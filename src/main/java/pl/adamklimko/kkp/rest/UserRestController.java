package pl.adamklimko.kkp.rest;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.adamklimko.kkp.dto.UserDto;
import pl.adamklimko.kkp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createUser(@Valid @RequestBody UserDto user) {
    userService.insertUser(user.toEntity());
  }
}
