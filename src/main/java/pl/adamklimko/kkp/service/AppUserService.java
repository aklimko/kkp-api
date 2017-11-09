package pl.adamklimko.kkp.service;

import pl.adamklimko.kkp.model.user.AppUser;

import java.util.List;

public interface AppUserService {
    AppUser find();

    AppUser findByUsername(String username);

    AppUser findById(int id);

    List<AppUser> findAll();

    void save(AppUser user);
}
