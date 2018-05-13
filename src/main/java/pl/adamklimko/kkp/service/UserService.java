package pl.adamklimko.kkp.service;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.dto.UserDto;
import pl.adamklimko.kkp.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
