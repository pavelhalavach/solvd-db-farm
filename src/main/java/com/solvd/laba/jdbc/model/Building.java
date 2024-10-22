package com.solvd.laba.jdbc.model;

import lombok.Data;

import java.util.List;


@Data
public class Building {
    private int id;
    private String naming;
    private BuildingType buildingType;
    private List<BuildingStorage<Vehicle>> buildingVehicleStorages;
    private List<BuildingStorage<Tool>> buildingTollStorages;
    private List<BuildingStorage<Animal>> buildingAnimalStorages;
}
