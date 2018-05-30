package pl.adamklimko.kkp.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.adamklimko.kkp.model.dto.UserDto;
import pl.adamklimko.kkp.model.entity.UserEntity;
import pl.adamklimko.kkp.repository.UserRepository;

class UserServiceTest {

  @InjectMocks
  private UserService userService;
  @InjectMocks
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  @InjectMocks
  private ModelMapper modelMapper;

  @Mock
  private UserRepository userRepository;

  public UserServiceTest() {
    MockitoAnnotations.initMocks(this);
    userService = new UserService(userRepository, bCryptPasswordEncoder, modelMapper);
  }

  @BeforeEach
  public void setup() {
    init();
  }

  @Test
  public void insertUser() {
    UserDto userDto = new UserDto("test", "testpassword");
    userService.insertUser(userDto);
    userService.getUser("test");
  }

  @Test
  public void getUser() {

  }

  private void init() {
    Mockito.when(userRepository.findAll()).thenReturn(getAllUsers());
  }

  private List<UserEntity> getAllUsers() {
    List<UserEntity> users = new ArrayList<>();
    users.add(new UserEntity("1","test","password"));
    users.add(new UserEntity("2","exuso","password"));
    return users;
  }
}