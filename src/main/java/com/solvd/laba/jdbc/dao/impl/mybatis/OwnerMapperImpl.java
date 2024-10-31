package com.solvd.laba.jdbc.dao.impl.mybatis;

import com.solvd.laba.jdbc.dao.OwnerDAO;
import com.solvd.laba.jdbc.model.Owner;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OwnerMapperImpl implements OwnerDAO {
    private static final MyBatisPool myBatisPool = MyBatisPool.getInstance();


    @Override
    public void insert(Owner owner) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            OwnerDAO ownerDAO = sqlSession.getMapper(OwnerDAO.class);
            ownerDAO.insert(owner);
            sqlSession.commit();
        }
    }

    @Override
    public void update(Owner owner) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            OwnerDAO ownerDAO = sqlSession.getMapper(OwnerDAO.class);
            ownerDAO.update(owner);
            sqlSession.commit();
        }
    }

    public void deleteById(int id) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            OwnerDAO ownerDAO = sqlSession.getMapper(OwnerDAO.class);
            ownerDAO.deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public Owner getById(int id) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            OwnerDAO ownerDAO = sqlSession.getMapper(OwnerDAO.class);
            return ownerDAO.getById(id);
        }
    }

    @Override
    public List<Owner> getAll() {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            OwnerDAO ownerDAO = sqlSession.getMapper(OwnerDAO.class);
            return ownerDAO.getAll();
        }
    }
}
