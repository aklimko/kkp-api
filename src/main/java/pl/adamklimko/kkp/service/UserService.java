package pl.adamklimko.kkp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.entity.UserEntity;
import pl.adamklimko.kkp.repository.UserRepository;

@Service
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void insertUser(UserEntity user) {
    userRepository.insert(user);
  }
}
