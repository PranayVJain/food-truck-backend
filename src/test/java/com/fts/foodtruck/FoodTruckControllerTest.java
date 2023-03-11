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
package com.fts.foodtruck;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fts.foodtruck.controller.FoodTruckController;
import com.fts.foodtruck.model.FoodTruck;
import com.fts.foodtruck.service.FoodTruckService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = FoodTruckController.class)
public class FoodTruckControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  FoodTruckService foodTruckService;

  @Test
  public void ifValidInputThenAddFoodTruck() throws Exception {
    final LocalDateTime localDateTime = LocalDateTime.now();
    final FoodTruck foodTruck = new FoodTruck("truckId", "Truck1", "My truck", localDateTime);
    Mockito.when(foodTruckService.saveFoodTruck(foodTruck)).thenReturn(foodTruck);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    mockMvc.perform(post("/v1/foodtrucks").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(foodTruck)))
        .andExpect(status().isCreated());
  }

  @Test
  public void ifGetAllFoodtruckBetweenDatesThenReturnList() throws Exception {
    final LocalDateTime fromDate = LocalDateTime.now();
    final LocalDateTime toDate = LocalDateTime.now().plusDays(1);
    final FoodTruck foodTruck1 = new FoodTruck("truckId", "Truck1", "My truck", fromDate);
    final FoodTruck foodTruck2 = new FoodTruck("truckId2", "Truck2", "My truck", toDate);
    Mockito.when(foodTruckService.getAllFoodTrucks(Mockito.any(), Mockito.any())).thenReturn(List.of(foodTruck1, foodTruck2));
    mockMvc.perform(
        get("/v1/foodtrucks?fromDate=" + fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
            + "&toDate=" + toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));
  }
}