package pl.adamklimko.kkp.error.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyExists extends ServiceException {

  public UsernameAlreadyExists(String message) {
    super(HttpStatus.CONFLICT, message);
  }
}
