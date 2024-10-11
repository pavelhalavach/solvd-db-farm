package com.solvd.laba.dao;

import com.solvd.laba.model.Crop;

import java.util.List;

public interface CropDAO {
    void insert(Crop crop);
    Crop getById(Integer id);
    List<Crop> getAll();
}
