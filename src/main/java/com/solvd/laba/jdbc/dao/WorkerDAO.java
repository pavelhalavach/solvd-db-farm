package com.solvd.laba.jdbc.dao;

import com.solvd.laba.jdbc.model.Worker;

import java.util.List;
import java.util.Map;

public interface WorkerDAO {
    void insert(Worker worker, int farmId);
    void update(Worker worker, int farmId);
    void insertDataToWorkerResponsibilities(Worker worker);
    void deleteById(int id);
    void deleteDataFromWorkerResponsibilities(Worker worker);
    Worker getById(int id);
    List<Worker> getAll();
}
