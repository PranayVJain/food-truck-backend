package com.fts.foodtruck.repository;

import com.fts.foodtruck.model.FoodTruck;
import java.util.List;
import java.util.Optional;


public interface FoodTruckRepository {

  FoodTruck save(final FoodTruck foodTruck);

  void update(final FoodTruck foodTruck);

  List<FoodTruck> findAll();

  Optional<FoodTruck> findById(final String id);
}