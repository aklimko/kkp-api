package pl.adamklimko.kkp.rest;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.adamklimko.kkp.dto.UserDto;
import pl.adamklimko.kkp.error.exceptions.UserNotFoundException;
import pl.adamklimko.kkp.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserRestController {

  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createUser(@Valid @RequestBody UserDto user) {
    userService.insertUser(user);
  }

  @GetMapping("{username}")
  public UserDto getUser(@PathVariable String username) throws UserNotFoundException {
    return userService.getUser(username);
  }
}
