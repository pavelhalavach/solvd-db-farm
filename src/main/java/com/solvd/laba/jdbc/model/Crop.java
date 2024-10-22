package com.solvd.laba.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
 public class Crop {
    private int id;
    private String name;
    private Date dateToSeed;
    private Date dateToHarvest;
}
