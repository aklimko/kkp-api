package pl.adamklimko.kkp.service;

import java.util.Optional;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.adamklimko.kkp.error.exceptions.UserNotFoundException;
import pl.adamklimko.kkp.model.dto.UserDto;
import pl.adamklimko.kkp.model.entity.UserEntity;
import pl.adamklimko.kkp.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final ModelMapper modelMapper;

  public void insertUser(UserDto user) {
    UserDto userWithEncodedPassword = encodeUserPassword(user);
    userRepository.insert(
        modelMapper.map(userWithEncodedPassword, UserEntity.class));
  }

  private UserDto encodeUserPassword(@NotNull UserDto user) {
    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    return user;
  }

  public UserDto getUser(String username) throws UserNotFoundException {
    Optional<UserEntity> user = userRepository.findByUsername(username);
    return modelMapper.map(
        user.orElseThrow(() -> new UserNotFoundException("User was not found")), UserDto.class);
  }
}
