
package com.fts.foodtruck.repository;

import com.fts.foodtruck.model.FoodTruck;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemFoodTruckRepository implements FoodTruckRepository {

  final Map<String, FoodTruck> foodTruckMap = new ConcurrentHashMap<>();

  @Override
  public FoodTruck save(FoodTruck foodTruck) {
    final String id = UUID.randomUUID().toString();
    FoodTruck createdFoodTruck = new FoodTruck(id, foodTruck.getName(), foodTruck.getDescription(),
        foodTruck.getDate());
    foodTruckMap.put(id, createdFoodTruck);
    return createdFoodTruck;
  }

  @Override
  public void update(FoodTruck foodTruck) {
    foodTruckMap.put(foodTruck.getId(), foodTruck);
  }

  @Override
  public List<FoodTruck> findAll() {
    return (List<FoodTruck>) foodTruckMap.values();
  }

  @Override
  public Optional<FoodTruck> findById(final String id) {
    Optional<FoodTruck> foodTruck = Optional.empty();
    if (foodTruckMap.get(id) != null) {
      foodTruck = Optional.of(foodTruckMap.get(id));
    }
    return foodTruck;
  }
}