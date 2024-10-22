package com.solvd.laba.jdbc.dao;

import com.solvd.laba.jdbc.model.Farm;

import java.util.List;

public interface FarmDAO {
    void insert(Farm farm);
    void update(Farm farm);
    void deleteById(int id);
    Farm getById(int id);
    List<Farm> getAll();
}
