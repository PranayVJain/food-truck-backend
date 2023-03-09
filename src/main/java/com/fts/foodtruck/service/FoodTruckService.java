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
package com.fts.foodtruck.service;

import com.fts.foodtruck.model.FoodTruck;
import com.fts.foodtruck.repository.FoodTruckRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FoodTruckService {

  private final FoodTruckRepository foodTruckRepository;

  public FoodTruckService(final FoodTruckRepository foodTruckRepository) {
    this.foodTruckRepository = foodTruckRepository;
  }

  public FoodTruck saveFoodTruck(final FoodTruck foodTruck) {
    return foodTruckRepository.save(foodTruck);
  }

  public List<FoodTruck> getAllFoodTrucks() {
    return foodTruckRepository.findAll();
  }

  public void updateFoodTruck(final FoodTruck foodTruck) {
    foodTruckRepository.updateFoodTruck(foodTruck);
  }


}