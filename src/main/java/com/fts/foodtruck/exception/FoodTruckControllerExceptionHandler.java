
package com.fts.foodtruck.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception controller class to handle different responses for different exceptions.
 */
@RestControllerAdvice
public class FoodTruckControllerExceptionHandler {

  @ExceptionHandler(FoodTruckNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public @ResponseBody ErrorResponse studentNotFoundException(final FoodTruckNotFoundException cause) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), cause.getMessage());
  }

  @ExceptionHandler(FoodTruckAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorResponse studentNotFoundException(final FoodTruckAlreadyExistsException cause) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), cause.getMessage());
  }
}