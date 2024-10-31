package com.solvd.laba.jdbc.dao.impl.mybatis;

import com.solvd.laba.jdbc.dao.ResponsibilityDAO;
import com.solvd.laba.jdbc.model.Responsibility;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponsibilityMapperImpl implements ResponsibilityDAO {
    private static final MyBatisPool myBatisPool = MyBatisPool.getInstance();

    @Override
    public void insert(Responsibility responsibility) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            ResponsibilityDAO responsibilityDAO = sqlSession.getMapper(ResponsibilityDAO.class);
            responsibilityDAO.insert(responsibility);
            sqlSession.commit();
        }
    }

    @Override
    public void update(Responsibility responsibility) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            ResponsibilityDAO responsibilityDAO = sqlSession.getMapper(ResponsibilityDAO.class);
            responsibilityDAO.update(responsibility);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteById(int id) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            ResponsibilityDAO responsibilityDAO = sqlSession.getMapper(ResponsibilityDAO.class);
            responsibilityDAO.deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public Responsibility getById(int id) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            ResponsibilityDAO responsibilityDAO = sqlSession.getMapper(ResponsibilityDAO.class);
            return responsibilityDAO.getById(id);
        }
    }

    @Override
    public List<Responsibility> getAll() {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            ResponsibilityDAO responsibilityDAO = sqlSession.getMapper(ResponsibilityDAO.class);
            return responsibilityDAO.getAll();
        }
    }


}
