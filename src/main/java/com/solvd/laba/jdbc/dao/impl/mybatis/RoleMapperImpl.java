package com.solvd.laba.jdbc.dao.impl.mybatis;

import com.solvd.laba.jdbc.dao.RoleDAO;
import com.solvd.laba.jdbc.dao.RoleDAO;
import com.solvd.laba.jdbc.model.Role;
import com.solvd.laba.jdbc.model.Role;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RoleMapperImpl implements RoleDAO {
    private static final MyBatisPool myBatisPool = MyBatisPool.getInstance();


    @Override
    public void insert(Role role) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            RoleDAO roleDAO = sqlSession.getMapper(RoleDAO.class);
            roleDAO.insert(role);
            sqlSession.commit();
        }
    }

    @Override
    public void update(Role role) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            RoleDAO roleDAO = sqlSession.getMapper(RoleDAO.class);
            roleDAO.update(role);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteByProfession(String profession) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            RoleDAO roleDAO = sqlSession.getMapper(RoleDAO.class);
            roleDAO.deleteByProfession(profession);
            sqlSession.commit();
        }
    }

    @Override
    public Role getById(int id) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            RoleDAO roleDAO = sqlSession.getMapper(RoleDAO.class);
            return roleDAO.getById(id);
        }
    }

    @Override
    public Role getByProfession(String profession) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            RoleDAO roleDAO = sqlSession.getMapper(RoleDAO.class);
            return roleDAO.getByProfession(profession);
        }
    }

    @Override
    public List<Role> getAll() {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            RoleDAO roleDAO = sqlSession.getMapper(RoleDAO.class);
            return roleDAO.getAll();
        }
    }
}
