package com.fts.foodtruck.controller;

import com.fts.foodtruck.model.FoodTruck;
import com.fts.foodtruck.service.FoodTruckService;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is the front controller of food truck service.
 */
@RestController
@RequestMapping("/v1/foodtrucks")
public class FoodTruckController {

  private static final Logger LOGGER = LoggerFactory.getLogger(FoodTruckController.class);

  private final FoodTruckService foodTruckService;

  public FoodTruckController(final FoodTruckService foodTruckService) {
    this.foodTruckService = foodTruckService;
  }

  /**
   * Create a new food truck.
   *
   * @param foodTruck
   * @return
   */
  @PostMapping(consumes = "application/json")
  public ResponseEntity<FoodTruck> createFoodTruck(@RequestBody FoodTruck foodTruck) {
    LOGGER.info("Received request to create a new food truck <{}>", foodTruck);
    final FoodTruck createdFoodTruck = foodTruckService.saveFoodTruck(foodTruck);
    LOGGER.info("Created a new food truck <{}>", foodTruck);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdFoodTruck);
  }

  /**
   * List all the available food trucks
   *
   * @return
   */
  @GetMapping(produces = "application/json")
  public ResponseEntity<List<FoodTruck>> getAllFoodTrucksByTimeRange(
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {
    LOGGER.info("Received request to get list of all food truck from <{}> to <{}>", fromDate, toDate);
    List<FoodTruck> foodTrucks;
    if (fromDate == null && toDate == null) {
      foodTrucks = foodTruckService.getAllFoodTrucks();
    } else {
      foodTrucks = foodTruckService.getAllFoodTrucks(fromDate, toDate);
    }
    return ResponseEntity.ok(foodTrucks);
  }

  /**
   * Update the food truck property.
   *
   * @param foodTruckId
   * @param foodTruck
   */
  @PutMapping("/{foodTruckId}")
  public void updateFoodTruck(@PathVariable("foodTruckId") String foodTruckId, @RequestBody FoodTruck foodTruck) {
    foodTruckService.updateFoodTruck(foodTruckId, foodTruck);
  }

}