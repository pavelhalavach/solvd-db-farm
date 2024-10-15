package com.solvd.laba.model;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Data
public class Building {
    @NonNull
    private Integer id;
    @NonNull
    private String naming;
    @NonNull
    private BuildingType buildingType;
    @NonNull
    private List<BuildingStorage<Vehicle>> buildingVehicleStorages;
    @NonNull
    private List<BuildingStorage<Tool>> buildingTollStorages;
    @NonNull
    private List<BuildingStorage<Animal>> buildingAnimalStorages;
}
