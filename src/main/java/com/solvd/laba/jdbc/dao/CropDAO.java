package com.solvd.laba.jdbc.dao;

import com.solvd.laba.jdbc.model.Crop;

import java.util.List;

public interface CropDAO {
    void insert(Crop crop);
    void update(Crop crop);
    void deleteByName(String name);
    Crop getById(int id);

    Crop getByName(String name);

    List<Crop> getAll();
}
