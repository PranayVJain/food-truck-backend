package com.fts.foodtruck.repository;

import com.fts.foodtruck.model.FoodTruck;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodTruckRepository extends JpaRepository<FoodTruck, String> {

  List<FoodTruck> findAllByAvailableDateBetween(final LocalDateTime fromDate, final LocalDateTime toDate);
}