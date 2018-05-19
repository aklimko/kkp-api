package pl.adamklimko.kkp.error;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.adamklimko.kkp.error.exceptions.ServiceException;
import pl.adamklimko.kkp.error.exceptions.UserNotFoundException;
import pl.adamklimko.kkp.error.exceptions.UsernameAlreadyExistsException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ UsernameAlreadyExistsException.class })
  public ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
    return new ResponseEntity<>(ex.formattedResponse(), ex.getStatus());
  }

  @ExceptionHandler({ UserNotFoundException.class })
  public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
    return new ResponseEntity<>(ex.formattedResponse(), ex.getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    String errorMessage = parseErrorMessage(ex);

    HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
    JsonNode formattedResponse =
        new ServiceException(badRequestStatus, errorMessage).formattedResponse();
    return handleExceptionInternal(ex, formattedResponse, headers, badRequestStatus, request);
  }

  private String parseErrorMessage(MethodArgumentNotValidException ex) {
    String[] errorMessage = new String[]{""};

    ex.getBindingResult().getFieldErrors().forEach(error ->
        errorMessage[0] += error.getField() + ": " + error.getDefaultMessage());
    ex.getBindingResult().getGlobalErrors().forEach(error ->
        errorMessage[0] += error.getObjectName() + ": " + error.getDefaultMessage());
    return errorMessage[0];
  }
}
