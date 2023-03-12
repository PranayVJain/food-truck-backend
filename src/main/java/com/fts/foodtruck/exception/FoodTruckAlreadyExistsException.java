
package com.fts.foodtruck.exception;

/**
 * Exception wrapper class for food truck already present.
 */
public class FoodTruckAlreadyExistsException extends RuntimeException {

  private final String message;

  public FoodTruckAlreadyExistsException(final String msg, final Throwable cause) {
    super(msg, cause);
    this.message = msg;
  }

  @Override
  public String getMessage() {
    return message;
  }
}