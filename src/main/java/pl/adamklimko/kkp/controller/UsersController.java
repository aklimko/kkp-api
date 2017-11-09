package pl.adamklimko.kkp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.model.user.Profile;
import pl.adamklimko.kkp.service.AppUserService;

@RestController
@RequestMapping("/users")
public class UsersController {
    private AppUserService appUserService;

    public UsersController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{id}/profile")
    public Profile getUserProfile(@PathVariable int id) {
        final AppUser user = appUserService.findById(id);
        if (user == null) {
            return null;
        }
        return user.getProfile();
    }
}