package com.solvd.laba.service.impl;

import com.solvd.laba.dao.WorkerDAO;
import com.solvd.laba.dao.impl.WorkerDAOImpl;
import com.solvd.laba.model.Worker;
import com.solvd.laba.service.ResponsibilityService;
import com.solvd.laba.service.WorkerService;

import java.util.List;

public class WorkerServiceImpl implements WorkerService {
    private final WorkerDAO workerDAO;
    private final ResponsibilityService responsibilityService;
    public WorkerServiceImpl() {
        this.workerDAO = new WorkerDAOImpl();
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
    public void update(Worker worker) {
        workerDAO.update(worker);
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
