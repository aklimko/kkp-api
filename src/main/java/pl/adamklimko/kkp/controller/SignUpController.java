package pl.adamklimko.kkp.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adamklimko.kkp.model.products.BoughtProducts;
import pl.adamklimko.kkp.model.rooms.CleanedRooms;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.model.user.Profile;
import pl.adamklimko.kkp.service.AppUserService;
import pl.adamklimko.kkp.service.BoughtProductsService;
import pl.adamklimko.kkp.service.CleanedRoomsService;
import pl.adamklimko.kkp.service.ProfileService;

@RestController
@RequestMapping("/sign_up")
public class SignUpController {
    private final AppUserService appUserService;
    private final ProfileService profileService;
    private final BoughtProductsService boughtProductsService;
    private final CleanedRoomsService cleanedRoomsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SignUpController(AppUserService appUserService, ProfileService profileService, BoughtProductsService boughtProductsService, CleanedRoomsService cleanedRoomsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserService = appUserService;
        this.profileService = profileService;
        this.boughtProductsService = boughtProductsService;
        this.cleanedRoomsService = cleanedRoomsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping()
    public boolean signUp(@RequestBody AppUser user) {
        final AppUser appUser = appUserService.findByUsername(user.getUsername());
        if (appUser != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        addEmptyUserData(user);
        appUserService.save(user);
        return true;
    }

    private void addEmptyUserData(AppUser user) {
        final Profile profile = new Profile();
        profileService.save(profile);
        user.setProfile(profile);

        final BoughtProducts boughtProducts = new BoughtProducts();
        boughtProductsService.save(boughtProducts);
        user.setBoughtProducts(boughtProducts);

        final CleanedRooms cleanedRooms = new CleanedRooms();
        cleanedRoomsService.save(cleanedRooms);
        user.setCleanedRooms(cleanedRooms);
    }
}
