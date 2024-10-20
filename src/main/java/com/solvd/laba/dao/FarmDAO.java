package com.solvd.laba.dao;

import com.solvd.laba.model.Farm;

import java.util.List;

public interface FarmDAO {
    void insert(Farm farm);
    void update(Farm farm);
    void deleteById(int id);
    Farm getById(int id);
    List<Farm> getAll();
}
