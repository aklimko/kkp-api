package pl.adamklimko.kkp.service;

import java.util.List;
import pl.adamklimko.kkp.model.user.AppUser;

public interface AppUserService {

  AppUser find();

  AppUser findByUsername(String username);

  AppUser findById(long id);

  List<AppUser> findAll();

  void save(AppUser user);
}
