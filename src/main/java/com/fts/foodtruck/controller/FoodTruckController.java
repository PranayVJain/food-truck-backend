/*
 * #%L
 * foodtruck
 *
 * %%
 * Copyright (C) 2018 - 2022 Nuance Communications Inc. All Rights Reserved.
 * %%
 *
 * The copyright to the computer program(s) herein is the property of
 * Nuance Communications Inc. The program(s) may be used and/or copied
 * only with the written permission from Nuance Communications Inc.
 * or in accordance with the terms and conditions stipulated in the
 * agreement/contract under which the program(s) have been supplied.
 * #L%
 */
package com.fts.foodtruck.controller;

import com.fts.foodtruck.model.FoodTruck;
import com.fts.foodtruck.service.FoodTruckService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public List<FoodTruck> getAllFoodTrucks() {
    return foodTruckService.getAllFoodTrucks();
  }

  /**
   * Update the food truck property.
   *
   * @param foodTruckId
   * @param foodTruck
   */
  @PutMapping("/{foodTruckId}")
  public void updateFoodTruck(@PathVariable("foodTruckId") int foodTruckId, @RequestBody FoodTruck foodTruck) {
    //TBD
  }

}