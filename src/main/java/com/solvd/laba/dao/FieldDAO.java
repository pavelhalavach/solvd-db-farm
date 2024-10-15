package com.solvd.laba.dao;

import com.solvd.laba.model.Field;

import java.util.List;

public interface FieldDAO {
    void insert(Field field, int farmId);
    void deleteById(int id);
    Field getById(int id);
    List<Field> getAllByFarmId(int farmId);
}
