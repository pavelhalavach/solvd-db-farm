package com.solvd.laba.model;

import lombok.Data;

import java.time.LocalDate;

@Data public class Crop {
    private int id;
    private String name;
    private LocalDate dateToSeed;
    private LocalDate dateToHarvest;
}
