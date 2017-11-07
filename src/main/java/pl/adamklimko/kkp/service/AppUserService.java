package pl.adamklimko.kkp.service;

import pl.adamklimko.kkp.model.user.AppUser;

public interface AppUserService {
    AppUser find();

    AppUser findByUsername(String username);

    void save(AppUser user);
}
