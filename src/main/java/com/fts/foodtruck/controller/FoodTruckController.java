package com.fts.foodtruck.controller;

import com.fts.foodtruck.model.FoodTruck;
import com.fts.foodtruck.service.FoodTruckService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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
    final FoodTruck createdFoodTruck = foodTruckService.saveFoodTruck(foodTruck);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdFoodTruck);
  }

  /**
   * List all the available food trucks
   *
   * @return
   */
  @GetMapping(produces = "application/json")
  public ResponseEntity<List<FoodTruck>> getAllFoodTrucks(
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fromDate,
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime toDate) {
    return ResponseEntity.ok(foodTruckService.getAllFoodTrucks(fromDate, toDate));
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