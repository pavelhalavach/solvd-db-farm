package com.solvd.laba.jdbc.dao.impl.mybatis;

import com.solvd.laba.jdbc.dao.FarmDAO;
import com.solvd.laba.jdbc.model.Farm;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class FarmMapperImpl implements FarmDAO {
    private static final MyBatisPool myBatisPool = MyBatisPool.getInstance();

    @Override
    public void insert(Farm farm) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            FarmDAO farmDAO = sqlSession.getMapper(FarmDAO.class);
            farmDAO.insert(farm);
            sqlSession.commit();
        }
    }

    @Override
    public void update(Farm farm) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            FarmDAO farmDAO = sqlSession.getMapper(FarmDAO.class);
            farmDAO.update(farm);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteById(int id) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            FarmDAO farmDAO = sqlSession.getMapper(FarmDAO.class);
            farmDAO.deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public Farm getById(int id) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            FarmDAO farmDAO = sqlSession.getMapper(FarmDAO.class);
            return farmDAO.getById(id);
        }
    }

    @Override
    public List<Farm> getAll() {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            FarmDAO farmDAO = sqlSession.getMapper(FarmDAO.class);
            return farmDAO.getAll();
        }
    }
}
