package pl.adamklimko.kkp.error;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.adamklimko.kkp.error.exceptions.UsernameAlreadyExists;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> errors = new ArrayList<>();

    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.add(error.getField() + ": " + error.getDefaultMessage()));
    ex.getBindingResult().getGlobalErrors().forEach(error ->
        errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));

    ApiError apiError = new ApiError(errors);
    return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({ UsernameAlreadyExists.class })
  public ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExists ex) {
    //TODO: Fix unique username error handling
    return new ResponseEntity<>(ex, ex.getStatus());
  }
}