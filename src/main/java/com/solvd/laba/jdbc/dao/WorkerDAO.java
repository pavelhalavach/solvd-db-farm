package com.solvd.laba.jdbc.dao;

import com.solvd.laba.jdbc.model.Worker;

import java.util.List;

public interface WorkerDAO {
    void insert(Worker worker, int farmId);
    void update(Worker worker, int farmId);
    void deleteById(int id);
    Worker getById(int id);
    List<Worker> getAll();
}
