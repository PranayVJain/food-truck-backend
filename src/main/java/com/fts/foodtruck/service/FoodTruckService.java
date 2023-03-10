package com.fts.foodtruck.service;

import com.fts.foodtruck.model.FoodTruck;
import com.fts.foodtruck.repository.FoodTruckRepository;
import java.util.List;
import java.util.Optional;
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

  public FoodTruck getFoodTruck(String id) {
    final Optional<FoodTruck> foodTruck = foodTruckRepository.findById(id);
    if (!foodTruck.isPresent()) {

    }
    return foodTruck.get();
  }

  public void updateFoodTruck(final FoodTruck foodTruck) {
    foodTruckRepository.update(foodTruck);
  }


}