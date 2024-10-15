package com.solvd.laba.model;

import lombok.*;

import java.time.LocalDate;

@Data public class Crop {
    private Integer id;
    private String name;
    private LocalDate dateToSeed;
    private LocalDate dateToHarvest;
}
