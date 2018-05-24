package pl.adamklimko.kkp.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.adamklimko.kkp.model.entity.UserEntity;
import pl.adamklimko.kkp.repository.UserRepository;

class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void insertUser() {
  }

  @Test
  void getUser() {
    List<UserEntity> users = userRepository.findAll();
    assertEquals(0, users.size());
  }
}