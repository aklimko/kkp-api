package pl.adamklimko.kkp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adamklimko.kkp.config.patch.json.Patch;
import pl.adamklimko.kkp.config.patch.json.PatchRequestBody;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.model.user.Profile;
import pl.adamklimko.kkp.service.AppUserService;
import pl.adamklimko.kkp.service.ProfileService;
import pl.adamklimko.kkp.util.UserUtil;

@RestController
@RequestMapping("/user")
public class ContextUserController {

  private final AppUserService appUserService;
  private final ProfileService profileService;

  public ContextUserController(AppUserService appUserService, ProfileService profileService) {
    this.appUserService = appUserService;
    this.profileService = profileService;
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
