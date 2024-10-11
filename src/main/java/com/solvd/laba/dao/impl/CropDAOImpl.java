package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.ConnectionPool;
import com.solvd.laba.dao.CropDAO;
import com.solvd.laba.model.Crop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CropDAOImpl implements CropDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String INSERT = "INSERT INTO crops VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM crops";
    private static final String SELECT_BY_ID = "SELECT " +
            "c.id AS crop_id, " +
            "c.name AS crop_name, " +
            "c.date_to_seed AS seeding_date, " +
            "c.date_to_harvest AS harvesting_date " +
            "FROM crops AS c" +
            "WHERE crop_id = (?)";

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
    public Crop getById(Integer id) {
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
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            crops = mapCrops(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return crops;
    }

    private Crop mapCrop(ResultSet resultSet) throws SQLException {
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

    private List<Crop> mapCrops(ResultSet resultSet) throws SQLException {
        List<Crop> crops = new ArrayList<>();
        while (resultSet.next()){
            crops.add(mapCrop(resultSet));
        }

        return crops;
    }
}
