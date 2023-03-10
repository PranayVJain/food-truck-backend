package com.fts.foodtruck.model;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class FoodTruck {

  private final String id;
  private final String name;
  private final String description;
  private final Instant date;
}