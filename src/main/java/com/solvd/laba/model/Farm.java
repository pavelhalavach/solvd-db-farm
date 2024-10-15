package com.solvd.laba.model;

import lombok.Data;

import java.util.List;

@Data public class Farm {
    private int id;
    private String name;
    private String location;
    private Owner owner;
    private List<Field> fields;
    private List<Worker> workers;
    private List<Building> buildings;
}
