package pl.adamklimko.kkp.controller;

import org.springframework.web.bind.annotation.*;
import pl.adamklimko.kkp.config.patch.json.Patch;
import pl.adamklimko.kkp.model.ActionType;
import pl.adamklimko.kkp.model.products.BoughtProducts;
import pl.adamklimko.kkp.model.products.MissingProducts;
import pl.adamklimko.kkp.model.products.ProductsEntry;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.model.user.Profile;
import pl.adamklimko.kkp.service.*;
import pl.adamklimko.kkp.util.HistoryUtil;
import pl.adamklimko.kkp.util.UserUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final AppUserService appUserService;
    private final BoughtProductsService boughtProductsService;
    private final MissingProductsService missingProductsService;
    private final HistoryService historyService;

    public ProductsController(AppUserService appUserService, BoughtProductsService boughtProductsService, MissingProductsService missingProductsService, HistoryService historyService) {
        this.appUserService = appUserService;
        this.boughtProductsService = boughtProductsService;
        this.missingProductsService = missingProductsService;
        this.historyService = historyService;
    }

    @GetMapping("/all")
    public Map<String, BoughtProducts> getBoughtProductsFromAllUsers() {
        final List<AppUser> users = appUserService.findAll();
        Map<String, BoughtProducts> usersBoughtProducts = new HashMap<>(users.size());
        for (AppUser user : users) {
            final Profile profile = user.getProfile();
            if (profile == null) {
                usersBoughtProducts.put(user.getUsername(), user.getBoughtProducts());
                continue;
            }
            final String fullName = user.getProfile().getFullName();
            if (fullName != null && fullName.length() > 0) {
                usersBoughtProducts.put(fullName, user.getBoughtProducts());
            } else {
                usersBoughtProducts.put(user.getUsername(), user.getBoughtProducts());
            }
        }
        return usersBoughtProducts;
    }

    @GetMapping("bought")
    public BoughtProducts getBoughtProducts() {
        return boughtProductsService.find();
    }

    @PatchMapping("bought")
    @Patch(id = Long.class, service = BoughtProductsService.class)
    public BoughtProducts patchBoughtProducts(@RequestBody BoughtProducts boughtProducts) {
        final AppUser user = appUserService.findByUsername(UserUtil.getUsernameFromContext());
        if (user == null) {
            return null;
        }
        final BoughtProducts userBoughtProducts = user.getBoughtProducts();
        if (userBoughtProducts == null) {
            user.setBoughtProducts(boughtProducts);
        } else {
            userBoughtProducts.addNewBoughtProducts(boughtProducts);
        }

        final MissingProducts missingProducts = missingProductsService.find();
        if (missingProducts != null) {
            missingProducts.removeMissingProducts(boughtProducts);
        }

        final BoughtProducts boughtProductsAfterAddition = user.getBoughtProducts();
        ProductsEntry productsEntry = new ProductsEntry(boughtProducts);
        historyService.save(HistoryUtil.getHistoryEntry(user, productsEntry, null, ActionType.DONE));
        return boughtProductsAfterAddition;
    }

    @GetMapping("missing")
    public MissingProducts getMissingProducts() {
        return missingProductsService.find();
    }

    @PatchMapping("missing")
    @Patch(id = Long.class, service = MissingProductsService.class)
    public MissingProducts patchMissingProducts(@RequestBody MissingProducts missingProducts) {
        final MissingProducts missingProductsFromDb = missingProductsService.find();
        if (missingProductsFromDb == null) {
            missingProductsService.save(missingProducts);
        } else {
            missingProductsFromDb.addNewMissingProducts(missingProducts);
            missingProductsService.save(missingProductsFromDb);
        }

        final AppUser user = appUserService.findByUsername(UserUtil.getUsernameFromContext());
        if (user == null) {
            return null;
        }
        final ProductsEntry productsEntry = new ProductsEntry(missingProducts);
        historyService.save(HistoryUtil.getHistoryEntry(user, productsEntry, null, ActionType.TO_BE_DONE));
        return missingProductsFromDb;
    }
}
