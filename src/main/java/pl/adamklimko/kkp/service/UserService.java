package pl.adamklimko.kkp.service;

import java.util.Optional;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.dto.UserDto;
import pl.adamklimko.kkp.model.entity.UserEntity;
import pl.adamklimko.kkp.error.exceptions.UserNotFoundException;
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

  public UserDto getUser(String username) throws UserNotFoundException {
    Optional<UserEntity> user = userRepository.findByUsername(username);
    return user
        .orElseThrow(() -> new UserNotFoundException("User was not found"))
        .toDto();
  }
}
