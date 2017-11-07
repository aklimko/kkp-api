package pl.adamklimko.kkp.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.adamklimko.kkp.config.patch.json.Patch;
import pl.adamklimko.kkp.config.patch.json.PatchRequestBody;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.model.user.Profile;
import pl.adamklimko.kkp.service.AppUserService;
import pl.adamklimko.kkp.service.ProfileService;
import pl.adamklimko.kkp.util.UserUtil;

@RestController
@RequestMapping("/user")
public class UserController {
    private final AppUserService appUserService;
    private final ProfileService profileService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(AppUserService appUserService, ProfileService profileService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserService = appUserService;
        this.profileService = profileService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        appUserService.save(user);
    }

    @GetMapping("/profile")
    public Profile getProfile() {
        return profileService.find();
    }

    @PatchMapping("/profile")
    @Patch(id = Integer.class, service = ProfileService.class)
    public Profile patchProfile(@PatchRequestBody Profile profile) {
        final AppUser user = appUserService.findByUsername(UserUtil.getUsernameFromContext());
        if (user.getProfile() == null) {
            user.setProfile(profile);
        }
        profileService.save(profile);
        return profile;
    }

}
