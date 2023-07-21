package com.fastcampus.toyboard.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ToyboardExceptionHandler {
  @ExceptionHandler(
      value = {
        HttpRequestMethodNotSupportedException.class,
        BindException.class,
        NumberFormatException.class,
        MethodArgumentTypeMismatchException.class
      })
  public String handleException(Exception e, HttpServletRequest request) {
    log.error("Handle Exception");
    log.error(
        "\nCause : {}\n URL : {}\n Message : {}\n",
        e.getCause(),
        request.getRequestURI(),
        e.getMessage());
    return "redirect:/badRequest";
  }

  @ExceptionHandler(value = {AccessDeniedException.class})
  public String accessDeniedException(Exception e, HttpServletRequest request) {
    log.error("Handle Exception");
    log.error(
        "\nCause : {}\n URL : {}\n Message : {}\n",
        e.getCause(),
        request.getRequestURI(),
        e.getMessage());
    return "redirect:/error403";
  }

  @ExceptionHandler(value = {Exception.class})
  public String handleOtherException(Exception e, HttpServletRequest request) {
    log.error("Other Exception : {}", e.toString());
    log.error(
        "\nCause : {}\n URL : {}\n Message : {}\n",
        e.getCause(),
        request.getRequestURI(),
        e.getMessage());
    return "redirect:/serverError";
  }
}
