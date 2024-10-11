package com.solvd.laba.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data public class Farm {
    @NonNull private Integer id;
    @NonNull private String name;
    @NonNull private String location;
    @NonNull private Owner owner;
    @NonNull private List<Field> fields;
    @NonNull private List<Worker> workers;
    @NonNull private List<Building> buildings;
}
