
package com.fts.foodtruck.exception;

/**
 * Exception wrapper class
 */
public class FoodTruckNotFoundException extends RuntimeException {

  private final String message;

  public FoodTruckNotFoundException(final String msg) {
    super(msg);
    this.message = msg;
  }

  @Override
  public String getMessage() {
    return message;
  }
}