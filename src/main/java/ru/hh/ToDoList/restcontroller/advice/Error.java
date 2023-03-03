package ru.hh.ToDoList.restcontroller.advice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import ru.hh.ToDoList.exception.NotFoundException;

@Data
public class Error {

  private HttpStatus status;
  private LocalDateTime timestamp = LocalDateTime.now();
  private String message;
  private Map<String, String> errors;

  public Error(BindException e) {
    status = HttpStatus.BAD_REQUEST;
    message = e.getMessage();
    errors = getErrors(e);
  }

  public Error(Throwable e) {
    status = HttpStatus.BAD_REQUEST;
    message = e.getMessage();
  }

  public Error(NotFoundException e) {
    status = HttpStatus.NOT_FOUND;
    message = e.getMessage();
  }

  private Map<String, String> getErrors(BindException be) {
    Map<String, String> map = new TreeMap<>();
    for (ObjectError error : be.getBindingResult().getAllErrors()) {
      map.put(error.getCodes()[0].substring(error.getCodes()[0].lastIndexOf(".") + 1), error.getDefaultMessage());
    }
    return map;
  }

}
