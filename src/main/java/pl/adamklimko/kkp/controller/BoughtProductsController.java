package pl.adamklimko.kkp.controller;

import org.springframework.web.bind.annotation.*;
import pl.adamklimko.kkp.config.patch.json.Patch;
import pl.adamklimko.kkp.model.BoughtProducts;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.service.AppUserService;
import pl.adamklimko.kkp.service.BoughtProductsService;
import pl.adamklimko.kkp.util.UserUtil;

@RestController
@RequestMapping("/bought_products")
public class BoughtProductsController {
    private final AppUserService appUserService;
    private final BoughtProductsService boughtProductsService;

    public BoughtProductsController(AppUserService appUserService, BoughtProductsService boughtProductsService) {
        this.appUserService = appUserService;
        this.boughtProductsService = boughtProductsService;
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
        boughtProductsService.save(boughtProductsAfterAddition);
        return boughtProductsAfterAddition;
    }
}
