package pl.adamklimko.kkp.service;

import pl.adamklimko.kkp.model.user.Profile;

public interface ProfileService {

  Profile find();

  void save(Profile profile);
}
