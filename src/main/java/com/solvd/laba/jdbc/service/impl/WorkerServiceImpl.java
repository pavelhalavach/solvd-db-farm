package com.solvd.laba.jdbc.service.impl;

import com.solvd.laba.jdbc.dao.WorkerDAO;
import com.solvd.laba.jdbc.dao.impl.jdbc.WorkerDAOImpl;
import com.solvd.laba.jdbc.dao.impl.mybatis.WorkerMapperImpl;
import com.solvd.laba.jdbc.model.Responsibility;
import com.solvd.laba.jdbc.model.Worker;
import com.solvd.laba.jdbc.service.ResponsibilityService;
import com.solvd.laba.jdbc.service.WorkerService;

import java.util.List;

public class WorkerServiceImpl implements WorkerService {
    private final WorkerDAO workerDAO;
    private final ResponsibilityService responsibilityService;
    public WorkerServiceImpl() {
//        this.workerDAO = new WorkerDAOImpl();
        this.workerDAO = new WorkerMapperImpl();
        this.responsibilityService = new ResponsibilityServiceImpl();
    }

    @Override
    public void insert(Worker worker, int farmId) {
        if (worker.getResponsibilities() != null){
            worker.getResponsibilities().forEach(responsibilityService::insert);
        }
        workerDAO.insert(worker, farmId);
    }

    @Override
    public void update(Worker worker, int farmId) {
        if (getById(worker.getId()) != null) {
            List<Responsibility> newResponsibilities = worker.getResponsibilities();
            List<Responsibility> oldResponsibilities = getById(worker.getId()).getResponsibilities();
            newResponsibilities.stream()
                    .filter(newRes -> !oldResponsibilities.contains(newRes))
                    .forEach(responsibilityService::insert);
            workerDAO.update(worker, farmId);
        }
    }

    @Override
    public void deleteById(int id) {
        workerDAO.deleteById(id);
    }

    @Override
    public Worker getById(int id) throws NullPointerException{
        return workerDAO.getById(id);
    }

    @Override
    public List<Worker> getAll() throws NullPointerException{
        return workerDAO.getAll();
    }
}
