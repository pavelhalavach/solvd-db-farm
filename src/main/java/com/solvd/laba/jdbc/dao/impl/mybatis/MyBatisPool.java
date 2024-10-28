package com.solvd.laba.jdbc.dao.impl.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisPool {

    private static MyBatisPool INSTANCE;
    private static SqlSessionFactory sqlSessionFactory;
    private static PooledDataSource dataSource;

    private MyBatisPool() {
        try {
            dataSource = new PooledDataSource();
            dataSource.setDriver("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/farm");
            dataSource.setUsername("root");
            dataSource.setPassword("1152");
            dataSource.setPoolMaximumActiveConnections(10);
            dataSource.setPoolMaximumIdleConnections(5);

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = builder.build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing MyBatisPool", e);
        }
    }

    public static MyBatisPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MyBatisPool();
        }
        return INSTANCE;
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    public void close() {
        if (dataSource != null) {
            dataSource.forceCloseAll();
        }
    }
}

