package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.ConnectionPool;
import com.solvd.laba.dao.CropDAO;
import com.solvd.laba.model.Crop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CropDAOImpl implements CropDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String INSERT = "INSERT INTO crops(" +
            "name, date_to_seed, date_to_harvest" +
            ") " +
            "VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE crops " +
            "SET name = ?, date_to_seed = ?, date_to_harvest = ?" +
            "WHERE id = ?";
    private static final String DELETE_BY_NAME = "DELETE FROM crops " +
            "WHERE name = ?";
    private static final String SELECT_ALL = "SELECT * FROM crops";
    private static final String SELECT_BY_ID = "SELECT " +
            "c.id AS crop_id, " +
            "c.name AS crop_name, " +
            "c.date_to_seed AS seeding_date, " +
            "c.date_to_harvest AS harvesting_date " +
            "FROM crops AS c " +
            "WHERE crop_id = ?";

    @Override
    public void insert(Crop crop) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1, crop.getName());
            preparedStatement.setDate(2, Date.valueOf(crop.getDateToSeed()));
            preparedStatement.setDate(3, Date.valueOf(crop.getDateToHarvest()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.executeQuery(SELECT_ALL);
            resultSet.last();
            crop.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public void update(Crop crop){
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setString(1, crop.getName());
            preparedStatement.setDate(2, Date.valueOf(crop.getDateToSeed()));
            preparedStatement.setDate(3, Date.valueOf(crop.getDateToHarvest()));
            preparedStatement.setInt(4, crop.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public void deleteByName(String name){
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_NAME)){
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public Crop getById(int id) {
        Crop crop;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            crop = mapCrop(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return crop;
    }

    @Override
    public List<Crop> getAll() {
        List<Crop> crops;
        Connection connection = connectionPool.getConnection();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            crops = mapCrops(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return crops;
    }

    public static Crop mapCrop(ResultSet resultSet) throws SQLException {
        Crop crop = null;
        int id = resultSet.getInt("crop_id");
        if (id != 0){
            crop = new Crop();
            crop.setId(id);
            crop.setName(resultSet.getString("crop_name"));
            crop.setDateToSeed(resultSet.getDate("seeding_date").toLocalDate());
            crop.setDateToHarvest(resultSet.getDate("harvesting_date").toLocalDate());
        }
        return crop;
    }

    public static List<Crop> mapCrops(ResultSet resultSet) throws SQLException {
        List<Crop> crops = new ArrayList<>();
        while (resultSet.next()){
            crops.add(mapCrop(resultSet));
        }
        return crops;
    }
}
