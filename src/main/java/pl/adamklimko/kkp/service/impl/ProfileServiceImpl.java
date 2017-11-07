package pl.adamklimko.kkp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.model.user.Profile;
import pl.adamklimko.kkp.repository.AppUserRepository;
import pl.adamklimko.kkp.repository.ProfileRepository;
import pl.adamklimko.kkp.service.ProfileService;
import pl.adamklimko.kkp.util.UserUtil;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, AppUserRepository appUserRepository) {
        this.profileRepository = profileRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Profile find() {
        final Profile profile = appUserRepository.findByUsername(UserUtil.getUsernameFromContext()).getProfile();
        if (profile == null) {
            return new Profile();
        }
        return profile;
    }

    @Override
    public void save(Profile profile) {
        profileRepository.save(profile);
    }
}
