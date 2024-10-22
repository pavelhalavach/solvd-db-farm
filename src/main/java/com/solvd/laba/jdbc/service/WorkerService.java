package com.solvd.laba.jdbc.service;

import com.solvd.laba.jdbc.model.Responsibility;
import com.solvd.laba.jdbc.model.Worker;

import java.util.List;

public interface WorkerService {
    void insert(Worker worker, int farmId);
    void update(Worker worker, int farmId);
    void deleteById(int id);
    Worker getById(int id);
    List<Worker> getAll();
}
