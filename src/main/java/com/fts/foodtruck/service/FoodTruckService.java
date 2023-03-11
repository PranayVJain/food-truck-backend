package com.fts.foodtruck.service;

import com.fts.foodtruck.exception.FoodTruckNotFoundException;
import com.fts.foodtruck.model.FoodTruck;
import com.fts.foodtruck.repository.FoodTruckRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class FoodTruckService {

  private final FoodTruckRepository foodTruckRepository;

  public FoodTruckService(final FoodTruckRepository foodTruckRepository) {
    this.foodTruckRepository = foodTruckRepository;
  }

  public FoodTruck saveFoodTruck(final FoodTruck foodTruck) {
    final String id = UUID.randomUUID().toString();
    final FoodTruck createdFoodTruck = new FoodTruck(id, foodTruck.getName(), foodTruck.getDescription(),
        foodTruck.getAvailableDate());
    return foodTruckRepository.save(createdFoodTruck);
  }

  public List<FoodTruck> getAllFoodTrucks(final LocalDateTime fromDate, final LocalDateTime toDate) {
    return foodTruckRepository.findAllByAvailableDateBetween(fromDate, toDate);
  }

  public FoodTruck getFoodTruck(String id) {
    final Optional<FoodTruck> foodTruck = foodTruckRepository.findById(id);
    if (!foodTruck.isPresent()) {
      throw new FoodTruckNotFoundException("Food truck with id " + id + " does not exist");
    }
    return foodTruck.get();
  }

  public void updateFoodTruck(final String id, final FoodTruck foodTruck) {
    final FoodTruck foodTruckToUpdate = getFoodTruck(id);
    foodTruckToUpdate.setName(foodTruck.getName());
    foodTruckRepository.save(foodTruckToUpdate);
  }


}