package com.solvd.laba.service;

import com.solvd.laba.model.Farm;

import java.util.List;

public interface FarmService {
    void insert(Farm farm);
    void update(Farm farm);
    void deleteById(int id);
    Farm getById(int id);
    List<Farm> getAll();
}
