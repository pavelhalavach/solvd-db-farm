package com.solvd.laba.jdbc.service.impl;

import com.solvd.laba.jdbc.dao.FarmDAO;
import com.solvd.laba.jdbc.dao.impl.jdbc.FarmDAOImpl;
import com.solvd.laba.jdbc.dao.impl.mybatis.FarmMapperImpl;
import com.solvd.laba.jdbc.model.*;
import com.solvd.laba.jdbc.service.FarmService;
import com.solvd.laba.jdbc.service.FieldService;
import com.solvd.laba.jdbc.service.OwnerService;
import com.solvd.laba.jdbc.service.WorkerService;

import java.util.List;

public class FarmServiceImpl implements FarmService {
    private final FarmDAO farmDAO;
    private final WorkerService workerService;
    private final FieldService fieldService;
    private final OwnerService ownerService;
    
    public FarmServiceImpl() {
//        this.farmDAO = new FarmDAOImpl();
        this.farmDAO = new FarmMapperImpl();
        this.workerService = new WorkerServiceImpl();
        this.fieldService = new FieldServiceImpl();
        this.ownerService = new OwnerServiceImpl();
    }
    
    @Override
    public void insert(Farm farm) {
        if (farm.getOwner() != null){
            ownerService.insert(farm.getOwner());
        }
        farmDAO.insert(farm);
        if (farm.getFields() != null){
            farm.getFields().forEach(field -> fieldService.insert(field, farm.getId()));
        }
        if (farm.getWorkers() != null){
            farm.getWorkers().forEach(worker -> workerService.insert(worker, farm.getId()));
        }
    }

    @Override
    public void update(Farm farm) {
        Owner oldOwner = getById(farm.getId()).getOwner();
        if (!oldOwner.equals(farm.getOwner())) {
            ownerService.deleteById(oldOwner.getId());
            ownerService.insert(farm.getOwner());
        }

        List<Field> newFields = farm.getFields();
        List<Field> oldFields = getById(farm.getId()).getFields();
        newFields.stream()
                .filter(newField -> !oldFields.contains(newField))
                .forEach(newField -> fieldService.insert(newField, farm.getId()));
        oldFields.stream()
                .filter(oldField -> !newFields.contains(oldField))
                .forEach(oldField -> fieldService.deleteById(oldField.getId()));

        List<Worker> newWorkers = farm.getWorkers();
        List<Worker> oldWorkers = getById(farm.getId()).getWorkers();
        newWorkers.stream()
                .filter(newWorker -> !oldWorkers.contains(newWorker))
                .forEach(newWorker -> workerService.insert(newWorker, farm.getId()));
        oldWorkers.stream()
                .filter(oldWorker -> !newWorkers.contains(oldWorker))
                .forEach(oldWorker -> workerService.deleteById(oldWorker.getId()));

        farmDAO.update(farm);
    }

    @Override
    public void deleteById(int id) {
        farmDAO.deleteById(id);
    }

    @Override
    public Farm getById(int id) {
        return farmDAO.getById(id);
    }

    @Override
    public List<Farm> getAll() {
        return farmDAO.getAll();
    }
}
