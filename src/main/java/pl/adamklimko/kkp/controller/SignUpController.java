package pl.adamklimko.kkp.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.service.AppUserService;

@RestController
@RequestMapping("/sign_up")
public class SignUpController {
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SignUpController(AppUserService appUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserService = appUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/signup")
    public boolean signUp(@RequestBody AppUser user) {
        final AppUser appUser = appUserService.findByUsername(user.getUsername());
        if (appUser != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        appUserService.save(user);
        return true;
    }
}
