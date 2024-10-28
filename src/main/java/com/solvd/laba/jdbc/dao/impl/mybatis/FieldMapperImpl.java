package com.solvd.laba.jdbc.dao.impl.mybatis;

import com.solvd.laba.jdbc.dao.CropDAO;
import com.solvd.laba.jdbc.dao.FieldDAO;
import com.solvd.laba.jdbc.model.Crop;
import com.solvd.laba.jdbc.model.Field;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldMapperImpl implements FieldDAO {
    private static final MyBatisPool myBatisPool = MyBatisPool.getInstance();

    @Override
    public void insert(Field field, int farmId) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            FieldDAO fieldDAO = sqlSession.getMapper(FieldDAO.class);
            Map<String, Object> params = new HashMap<>();
            params.put("field", field);
            params.put("farmId", farmId);
            fieldDAO.insertField(params);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteById(int id) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            FieldDAO fieldDAO = sqlSession.getMapper(FieldDAO.class);
            Map<String, Object> params = new HashMap<>();
            fieldDAO.deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public Field getById(int id) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            FieldDAO fieldDAO = sqlSession.getMapper(FieldDAO.class);
            return fieldDAO.getById(id);
        }
    }

    @Override
    public List<Field> getAllByFarmId(int farmId) {
        try (SqlSession sqlSession = myBatisPool.getSqlSession()) {
            FieldDAO fieldDAO = sqlSession.getMapper(FieldDAO.class);
            return fieldDAO.getAllByFarmId(farmId);
        }
    }

    @Override
    public void insertField(Map<String, Object> params) {

    }
}
