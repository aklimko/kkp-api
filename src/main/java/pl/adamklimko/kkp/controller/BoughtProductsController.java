package pl.adamklimko.kkp.controller;

import org.springframework.web.bind.annotation.*;
import pl.adamklimko.kkp.config.patch.json.Patch;
import pl.adamklimko.kkp.model.ActionType;
import pl.adamklimko.kkp.model.BoughtProducts;
import pl.adamklimko.kkp.model.ProductsEntry;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.model.user.Profile;
import pl.adamklimko.kkp.service.AppUserService;
import pl.adamklimko.kkp.service.BoughtProductsService;
import pl.adamklimko.kkp.service.HistoryService;
import pl.adamklimko.kkp.service.ProductsEntryService;
import pl.adamklimko.kkp.util.HistoryUtil;
import pl.adamklimko.kkp.util.UserUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bought_products")
public class BoughtProductsController {
    private final AppUserService appUserService;
    private final BoughtProductsService boughtProductsService;
    private final ProductsEntryService productsEntryService;
    private final HistoryService historyService;

    public BoughtProductsController(AppUserService appUserService, BoughtProductsService boughtProductsService, ProductsEntryService productsEntryService, HistoryService historyService) {
        this.appUserService = appUserService;
        this.boughtProductsService = boughtProductsService;
        this.productsEntryService = productsEntryService;
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

    @GetMapping()
    public BoughtProducts getBoughtProducts() {
        return boughtProductsService.find();
    }

    @PatchMapping()
    @Patch(id = Integer.class, service = BoughtProductsService.class)
    public BoughtProducts patchBoughtProducts(@RequestBody BoughtProducts boughtProducts) {
        final AppUser user = appUserService.findByUsername(UserUtil.getUsernameFromContext());
        final BoughtProducts userBoughtProducts = user.getBoughtProducts();
        if (userBoughtProducts == null) {
            user.setBoughtProducts(boughtProducts);
        } else {
            userBoughtProducts.addNewBoughtProducts(boughtProducts);
        }

        final BoughtProducts boughtProductsAfterAddition = user.getBoughtProducts();
//        boughtProductsService.save(boughtProductsAfterAddition);
//        productsEntryService.save(boughtProducts);
        ProductsEntry productsEntry = new ProductsEntry(boughtProducts);
        historyService.save(HistoryUtil.getHistoryEntry(user, productsEntry, null, ActionType.DONE));
        return boughtProductsAfterAddition;
    }
}
