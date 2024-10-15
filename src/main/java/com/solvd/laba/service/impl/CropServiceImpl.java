package com.solvd.laba.service.impl;

import com.solvd.laba.dao.CropDAO;
import com.solvd.laba.dao.impl.CropDAOImpl;
import com.solvd.laba.model.Crop;
import com.solvd.laba.service.CropService;

import java.util.List;

public class CropServiceImpl implements CropService {

    private final CropDAO cropDAO;

    public CropServiceImpl() {
        this.cropDAO = new CropDAOImpl();
    }

    @Override
    public void insert(Crop crop) {
        cropDAO.insert(crop);
    }

    @Override
    public void update(Crop crop){
        cropDAO.update(crop);
    }

    @Override
    public void deleteByName(String name) {
        cropDAO.deleteByName(name);
    }

    @Override
    public Crop getById(int id) throws NullPointerException{
        return cropDAO.getById(id);
    }

    @Override
    public List<Crop> getAll() throws NullPointerException{
        return cropDAO.getAll();
    }
}
