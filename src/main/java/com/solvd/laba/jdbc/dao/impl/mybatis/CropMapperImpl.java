package com.solvd.laba.jdbc.dao.impl.mybatis;

import com.solvd.laba.jdbc.dao.CropDAO;
import com.solvd.laba.jdbc.model.Crop;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CropMapperImpl implements CropDAO {
    private static final MyBatisPool myBatisPool = MyBatisPool.getInstance();


    @Override
    public void insert(Crop crop) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            CropDAO cropDAO = sqlSession.getMapper(CropDAO.class);
            cropDAO.insert(crop);
            sqlSession.commit();
        }
    }

    @Override
    public void update(Crop crop) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            CropDAO cropDAO = sqlSession.getMapper(CropDAO.class);
            cropDAO.update(crop);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteByName(String name) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            CropDAO cropDAO = sqlSession.getMapper(CropDAO.class);
            cropDAO.deleteByName(name);
            sqlSession.commit();
        }
    }

    @Override
    public Crop getById(int id) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            CropDAO cropDAO = sqlSession.getMapper(CropDAO.class);
            return cropDAO.getById(id);
        }
    }

    @Override
    public Crop getByName(String name) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            CropDAO cropDAO = sqlSession.getMapper(CropDAO.class);
            return cropDAO.getByName(name);
        }
    }

    @Override
    public List<Crop> getAll() {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            CropDAO cropDAO = sqlSession.getMapper(CropDAO.class);
            return cropDAO.getAll();
        }
    }
}
