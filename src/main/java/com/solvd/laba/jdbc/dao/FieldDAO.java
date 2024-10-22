package com.solvd.laba.jdbc.dao;

import com.solvd.laba.jdbc.model.Field;

import java.util.List;

public interface FieldDAO {
    void insert(Field field, int farmId);
    void deleteById(int id);
    Field getById(int id);
    List<Field> getAllByFarmId(int farmId);
}
