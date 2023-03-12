package com.fts.foodtruck.service;

import com.fts.foodtruck.exception.FoodTruckNotFoundException;
import com.fts.foodtruck.model.FoodTruck;
import com.fts.foodtruck.repository.FoodTruckRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * This class handles all the business logic for the food truck.
 */
@Service
public class FoodTruckService {

  private static final Logger LOGGER = LoggerFactory.getLogger(FoodTruckService.class);

  private final FoodTruckRepository foodTruckRepository;

  public FoodTruckService(final FoodTruckRepository foodTruckRepository) {
    this.foodTruckRepository = foodTruckRepository;
  }

  public FoodTruck saveFoodTruck(final FoodTruck foodTruck) {
    final String id = UUID.randomUUID().toString();
    LOGGER.info("Food truck id <{}>", id);
    final FoodTruck createdFoodTruck = new FoodTruck(id, foodTruck.getName(), foodTruck.getDescription(),
        foodTruck.getAvailableDate());
    return foodTruckRepository.save(createdFoodTruck);
  }

  public List<FoodTruck> getAllFoodTrucks(final LocalDateTime fromDate, final LocalDateTime toDate) {
    return foodTruckRepository.findAllByAvailableDateBetween(fromDate, toDate);
  }

  public List<FoodTruck> getAllFoodTrucks() {
    LOGGER.info("Get all food trucks");
    return foodTruckRepository.findAll();
  }

  public FoodTruck getFoodTruck(String id) {
    LOGGER.info("Get food truck with id <{}>", id);
    final Optional<FoodTruck> foodTruck = foodTruckRepository.findById(id);
    if (!foodTruck.isPresent()) {
      throw new FoodTruckNotFoundException("Food truck with id " + id + " does not exist");
    }
    return foodTruck.get();
  }

  public void updateFoodTruck(final String id, final FoodTruck foodTruck) {
    final FoodTruck foodTruckToUpdate = getFoodTruck(id);
    if (StringUtils.isNotEmpty(foodTruck.getName())) {
      foodTruckToUpdate.setName(foodTruck.getName());
    }
    if (StringUtils.isNotEmpty(foodTruck.getDescription())) {
      foodTruckToUpdate.setDescription(foodTruck.getDescription());
    }
    if (foodTruck.getAvailableDate() != null) {
      foodTruckToUpdate.setAvailableDate(foodTruck.getAvailableDate());
    }
    foodTruckRepository.save(foodTruckToUpdate);
  }


}