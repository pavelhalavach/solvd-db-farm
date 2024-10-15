package com.solvd.laba.service;

import com.solvd.laba.model.Worker;

import java.util.List;

public interface WorkerService {
    void insert(Worker worker, int farmId);
    void update(Worker worker);
    void deleteById(int id);
    Worker getById(int id);
    List<Worker> getAll();
}
