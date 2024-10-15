package com.solvd.laba.service;

import com.solvd.laba.model.Field;

import java.util.List;

public interface FieldService {
    void insert(Field field, int farmId);
    void deleteById(int id);
    Field getById(int id);
    List<Field> getAllByFarmId(int farmId);
}
