package com.solvd.laba.jdbc.service;

import com.solvd.laba.jdbc.model.Crop;

import java.util.List;

public interface CropService {
    void insert(Crop crop);
    void update(Crop crop);
    void deleteByName(String name);
    Crop getById(int id);
    List<Crop> getAll();
}
