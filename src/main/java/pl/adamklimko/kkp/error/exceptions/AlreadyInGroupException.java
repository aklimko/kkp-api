package pl.adamklimko.kkp.error.exceptions;

import org.springframework.http.HttpStatus;

public class AlreadyInGroupException extends ServiceException {

  public AlreadyInGroupException(String message) {
    super(HttpStatus.CONFLICT, message);
  }
}
