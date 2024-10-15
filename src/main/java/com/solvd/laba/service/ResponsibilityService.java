package com.solvd.laba.service;

import com.solvd.laba.model.Responsibility;

import java.util.List;

public interface ResponsibilityService {
    void insert(Responsibility responsibility);
    void update(Responsibility responsibility);
    void deleteById(int id);
    Responsibility getById(int id);
    List<Responsibility> getAll();
}
