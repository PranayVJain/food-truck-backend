
package com.fts.foodtruck.service;

import com.fts.foodtruck.model.FoodTruck;
import com.fts.foodtruck.repository.FoodTruckRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FoodTruckServiceTest {

  @Mock
  private FoodTruckRepository foodTruckRepository;

  private FoodTruckService foodTruckService;

  @BeforeEach
  public void init() {
    this.foodTruckService = new FoodTruckService(foodTruckRepository);
  }

  @Test
  public void ifSaveFoodTruckThenPersist() {
    FoodTruck foodTruck = new FoodTruck();
    foodTruck.setName("Truck1");
    foodTruck.setDescription("Desc");
    foodTruck.setAvailableDate(LocalDateTime.now());
    Mockito.when(foodTruckRepository.save(Mockito.any())).thenReturn(foodTruck);
    Assertions.assertNotNull(foodTruckService.saveFoodTruck(foodTruck));
  }

  @Test
  public void ifGetAllFoodTrucksThenReturnList() {
    final FoodTruck foodTruck = new FoodTruck("Id", "name","desc",LocalDateTime.now());
    List<FoodTruck> foodTrucks = List.of(foodTruck);
    Mockito.when(foodTruckRepository.findAll()).thenReturn(foodTrucks);
    Assertions.assertNotNull(foodTruckService.getAllFoodTrucks());
  }
}