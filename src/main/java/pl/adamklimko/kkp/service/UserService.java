package pl.adamklimko.kkp.service;

import javax.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.dto.UserDto;
import pl.adamklimko.kkp.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public void insertUser(UserDto user) {
    UserDto userWithEncodedPassword = encodeUserPassword(user);
    userRepository.insert(userWithEncodedPassword.toEntity());
  }

  private UserDto encodeUserPassword(@NotNull UserDto user) {
    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    return user;
  }
}
