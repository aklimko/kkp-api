package pl.adamklimko.kkp.error.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends ServiceException {

  public UsernameAlreadyExistsException(String message) {
    super(HttpStatus.CONFLICT, message);
  }
}
