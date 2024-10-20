package com.solvd.laba.dao;

import com.solvd.laba.model.Crop;

import java.util.List;

public interface CropDAO {
    void insert(Crop crop);
    void update(Crop crop);
    void deleteByName(String name);
    Crop getById(int id);

    Crop getByName(String name);

    List<Crop> getAll();
}
