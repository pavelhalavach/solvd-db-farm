package com.solvd.laba.jdbc.dao.impl.mybatis;

import com.solvd.laba.jdbc.dao.WorkerDAOMyBatis;
import com.solvd.laba.jdbc.model.Worker;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerMapperImpl implements WorkerDAOMyBatis {
    private static final MyBatisPool myBatisPool = MyBatisPool.getInstance();

    @Override
    public void insert(Worker worker, int farmId) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            WorkerDAOMyBatis workerDAO = sqlSession.getMapper(WorkerDAOMyBatis.class);
            Map<String, Object> params = new HashMap<>();
            params.put("worker", worker);
            params.put("farmId", farmId);
            workerDAO.insertWorker(params);
            workerDAO.insertDataToWorkerResponsibilities(worker);
            sqlSession.commit();
        }
    }

    @Override
    public void update(Worker worker, int farmId) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            WorkerDAOMyBatis workerDAO = sqlSession.getMapper(WorkerDAOMyBatis.class);
            Map<String, Object> params = new HashMap<>();
            params.put("worker", worker);
            params.put("farmId", farmId);
            workerDAO.updateWorker(params);
            workerDAO.deleteDataFromWorkerResponsibilities(worker);
            workerDAO.insertDataToWorkerResponsibilities(worker);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteById(int id) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            WorkerDAOMyBatis workerDAO = sqlSession.getMapper(WorkerDAOMyBatis.class);
            workerDAO.deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteDataFromWorkerResponsibilities(Worker worker) {

    }

    @Override
    public Worker getById(int id) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            WorkerDAOMyBatis workerDAO = sqlSession.getMapper(WorkerDAOMyBatis.class);
            return workerDAO.getById(id);
        }
    }

    @Override
    public List<Worker> getAll() {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            WorkerDAOMyBatis workerDAO = sqlSession.getMapper(WorkerDAOMyBatis.class);
            return workerDAO.getAll();
        }
    }

    @Override
    public void insertDataToWorkerResponsibilities(Worker worker) {

    }

    @Override
    public void insertWorker(Map<String, Object> params) {

    }

    @Override
    public void updateWorker(Map<String, Object> map) {

    }
}
