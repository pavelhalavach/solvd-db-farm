package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.ConnectionPool;
import com.solvd.laba.dao.RoleDAO;
import com.solvd.laba.model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String INSERT = "INSERT INTO roles (profession) VALUES (?)";
    private static final String DELETE_BY_PROFESSION = "DELETE FROM roles WHERE profession = ?";
    private static final String SELECT_ALL = "SELECT id AS role_id, profession as role_profession FROM roles";
    private static final String SELECT_BY_ID = "SELECT " +
            "id AS role_id, " +
            "profession AS role_profession, " +
            "FROM roles " +
            "WHERE role_id = ?";

    @Override
    public void insert(Role role) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1, role.getProfession());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.executeQuery(SELECT_ALL);
            resultSet.last();
            role.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public void deleteByProfession(String profession){
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_PROFESSION)){
            preparedStatement.setString(1, profession);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public Role getById(int id) {
        Role role;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            role = mapRole(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return role;
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles;
        Connection connection = connectionPool.getConnection();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            roles = mapRoles(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return roles;
    }

    public static Role mapRole(ResultSet resultSet) throws SQLException {
        Role role = null;
        int id = resultSet.getInt("role_id");
        if (id != 0){
            role = new Role();
            role.setId(id);
            role.setProfession(resultSet.getString("role_profession"));
        }
        return role;
    }

    public static List<Role> mapRoles(ResultSet resultSet) throws SQLException {
        List<Role> roles = new ArrayList<>();
        while (resultSet.next()){
            roles.add(mapRole(resultSet));
        }
        return roles;
    }
}
