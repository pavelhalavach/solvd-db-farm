package com.solvd.laba.jdbc.dao;

import java.util.Map;

public interface WorkerDAOMyBatis extends WorkerDAO{
    void updateWorker(Map<String, Object> map);
    void insertWorker(Map<String, Object> map);
}
