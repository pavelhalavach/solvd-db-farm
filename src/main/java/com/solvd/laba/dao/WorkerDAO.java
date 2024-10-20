package com.solvd.laba.dao;

import com.solvd.laba.model.Worker;

import java.util.List;

public interface WorkerDAO {
    void insert(Worker worker, int farmId);
    void update(Worker worker, int farmId);
    void deleteById(int id);
    Worker getById(int id);
    List<Worker> getAll();
}
