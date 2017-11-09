package pl.adamklimko.kkp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.model.user.Profile;
import pl.adamklimko.kkp.service.AppUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final AppUserService appUserService;

    public UsersController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{id}/profile")
    public Profile getUserProfileById(@PathVariable int id) {
        return getUserProfile(appUserService.findById(id));
    }

    @GetMapping("/{username}/profile")
    public Profile getUserProfileByUsername(@PathVariable String username) {
        return getUserProfile(appUserService.findByUsername(username));
    }

    private Profile getUserProfile(AppUser user) {
        if (user == null) {
            return null;
        }
        return user.getProfile();
    }

    @GetMapping("/profiles")
    public Map<String, Profile> getAllUsersProfiles() {
        final List<AppUser> users = appUserService.findAll();
        final Map<String, Profile> usersProfiles = new HashMap<>(users.size());
        for (AppUser user : users) {
            usersProfiles.put(user.getUsername(), user.getProfile());
        }
        return usersProfiles;
    }
}
