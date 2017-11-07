package pl.adamklimko.kkp.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.repository.AppUserRepository;
import pl.adamklimko.kkp.service.AppUserService;
import pl.adamklimko.kkp.util.UserUtil;

import static java.util.Collections.emptyList;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser find() {
        return appUserRepository.findByUsername(UserUtil.getUsernameFromContext());
    }

    @Override
    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public void save(AppUser user) {
        appUserRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(appUser.getUsername(), appUser.getPassword(), emptyList());
    }
}
