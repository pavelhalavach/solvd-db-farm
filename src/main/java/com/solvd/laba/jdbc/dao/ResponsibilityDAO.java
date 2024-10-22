package com.solvd.laba.jdbc.dao;

import com.solvd.laba.jdbc.model.Responsibility;

import java.util.List;

public interface ResponsibilityDAO {
    void insert(Responsibility responsibility);
    void update(Responsibility responsibility);
    void deleteById(int id);
    Responsibility getById(int id);
    List<Responsibility> getAll();
}
