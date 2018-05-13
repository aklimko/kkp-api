package pl.adamklimko.kkp.error.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ServiceException {

  public UserNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
