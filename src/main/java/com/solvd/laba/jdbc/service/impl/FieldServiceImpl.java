package com.solvd.laba.jdbc.service.impl;

import com.solvd.laba.jdbc.dao.FieldDAO;
import com.solvd.laba.jdbc.dao.impl.jdbc.FieldDAOImpl;
import com.solvd.laba.jdbc.dao.impl.mybatis.FieldMapperImpl;
import com.solvd.laba.jdbc.model.Field;
import com.solvd.laba.jdbc.service.CropService;
import com.solvd.laba.jdbc.service.FieldService;

import java.util.List;

public class FieldServiceImpl implements FieldService {
    private final FieldDAO fieldDAO;
    private final CropService cropService;

    public FieldServiceImpl(){
//        this.fieldDAO = new FieldDAOImpl();
        this.fieldDAO = new FieldMapperImpl();
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
