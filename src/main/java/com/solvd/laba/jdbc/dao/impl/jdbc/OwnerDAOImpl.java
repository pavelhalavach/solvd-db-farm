package com.solvd.laba.jdbc.dao.impl.jdbc;

import com.solvd.laba.jdbc.dao.OwnerDAO;
import com.solvd.laba.jdbc.model.Owner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerDAOImpl implements OwnerDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String INSERT = "INSERT INTO owners (" +
            "first_name, second_name" +
            ") VALUES (?,?) " +
            "ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id)";
    private static final String UPDATE = "UPDATE owners SET first_name = ?, second_name = ? " +
            "WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM owners " +
            "WHERE id = ?";
    private static final String SELECT_ALL = "SELECT " +
            "id AS owner_id, " +
            "first_name AS owner_first_name, " +
            "second_name AS owner_second_name " +
            "FROM owners " +
            "ORDER BY id";
    private static final String SELECT_BY_ID = "SELECT " +
            "id AS owner_id, " +
            "first_name AS owner_first_name, " +
            "second_name AS owner_second_name " +
            "FROM owners " +
            "WHERE id = ?";

    @Override
    public void insert(Owner owner) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1, owner.getFirstName());
            preparedStatement.setString(2, owner.getSecondName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                owner.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public void update(Owner owner) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setString(1, owner.getFirstName());
            preparedStatement.setString(2, owner.getSecondName());
            preparedStatement.setInt(3, owner.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public void deleteById(int id) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public Owner getById(int id) {
        Owner owner = null;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                owner = mapOwner(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return owner;
    }

    @Override
    public List<Owner> getAll() {
        List<Owner> owners;
        Connection connection = connectionPool.getConnection();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            owners = mapOwners(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return owners;
    }

    public static Owner mapOwner(ResultSet resultSet) throws SQLException {
        Owner owner = null;
        int id = resultSet.getInt("owner_id");
        if (id != 0){
            owner = new Owner();
            owner.setId(id);
            owner.setFirstName(resultSet.getString("owner_first_name"));
            owner.setSecondName(resultSet.getString("owner_second_name"));
        }
        return owner;
    }

    public static List<Owner> mapOwners(ResultSet resultSet) throws SQLException {
        List<Owner> owners = new ArrayList<>();
        while (resultSet.next()){
            owners.add(mapOwner(resultSet));
        }
        return owners;
    }
}
