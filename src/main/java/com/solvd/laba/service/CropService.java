package com.solvd.laba.service;

import com.solvd.laba.model.Crop;

import java.util.List;

public interface CropService {
    void insert(Crop crop);
    void update(Crop crop);
    void deleteByName(String name);
    Crop getById(int id);
    List<Crop> getAll();
}
