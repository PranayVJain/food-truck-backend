package com.fts.foodtruck.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FoodTruck {

  @Id
  private String id;
  private String name;
  private String description;
  private LocalDateTime availableDate;

}