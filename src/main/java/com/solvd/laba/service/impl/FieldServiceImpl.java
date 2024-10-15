package com.solvd.laba.service.impl;

import com.solvd.laba.dao.FieldDAO;
import com.solvd.laba.dao.impl.FieldDAOImpl;
import com.solvd.laba.model.Field;
import com.solvd.laba.service.CropService;
import com.solvd.laba.service.FieldService;

import java.util.List;

public class FieldServiceImpl implements FieldService {
    private final FieldDAO fieldDAO;
    private final CropService cropService;

    public FieldServiceImpl(){
        this.fieldDAO = new FieldDAOImpl();
        this.cropService = new CropServiceImpl();
    }

    @Override
    public void insert(Field field, int farmId) {
        if (field.getCrop() != null){
            cropService.insert(field.getCrop());
        }
        fieldDAO.insert(field, farmId);
    }

    @Override
    public void deleteById(int id) {
        fieldDAO.deleteById(id);
    }

    @Override
    public Field getById(int id) {
        return fieldDAO.getById(id);
    }

    @Override
    public List<Field> getAllByFarmId(int farmId) {
        return fieldDAO.getAllByFarmId(farmId);
    }
}
