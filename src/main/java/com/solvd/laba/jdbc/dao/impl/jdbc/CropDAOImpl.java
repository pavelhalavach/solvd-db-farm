package com.solvd.laba.jdbc.dao.impl.jdbc;

import com.solvd.laba.jdbc.dao.CropDAO;
import com.solvd.laba.jdbc.model.Crop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CropDAOImpl implements CropDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String INSERT = "INSERT INTO crops(" +
            "name, date_to_seed, date_to_harvest" +
            ") " +
            "VALUES (?,?,?) " +
            "ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id)";
    private static final String UPDATE = "UPDATE crops " +
            "SET name = ?, date_to_seed = ?, date_to_harvest = ? " +
            "WHERE id = ?";
    private static final String DELETE_BY_NAME = "DELETE FROM crops " +
            "WHERE name = ?";
    private static final String SELECT_ALL = "SELECT " +
            "c.id AS crop_id, " +
            "c.name AS crop_name, " +
            "c.date_to_seed AS crop_seeding_date, " +
            "c.date_to_harvest AS crop_harvesting_date " +
            "FROM crops AS c " +
            "ORDER BY c.id";
    private static final String SELECT_BY_ID = "SELECT " +
            "c.id AS crop_id, " +
            "c.name AS crop_name, " +
            "c.date_to_seed AS crop_seeding_date, " +
            "c.date_to_harvest AS crop_harvesting_date " +
            "FROM crops AS c " +
            "WHERE c.id = ?";

    private static final String SELECT_BY_NAME = "SELECT " +
            "c.id AS crop_id, " +
            "c.name AS crop_name, " +
            "c.date_to_seed AS crop_seeding_date, " +
            "c.date_to_harvest AS crop_harvesting_date " +
            "FROM crops AS c " +
            "WHERE c.name = ?";

    @Override
    public void insert(Crop crop) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1, crop.getName());
            preparedStatement.setDate(2, crop.getDateToSeed());
            preparedStatement.setDate(3, crop.getDateToHarvest());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                crop.setId(resultSet.getInt(1));
            }
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
            preparedStatement.setDate(2, crop.getDateToSeed());
            preparedStatement.setDate(3, crop.getDateToHarvest());
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
        Crop crop = null;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                crop = mapCrop(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return crop;
    }

    @Override
    public Crop getByName(String name) {
        Crop crop = null;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME)){
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                crop = mapCrop(resultSet);
            }
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
            crop.setDateToSeed(resultSet.getDate("crop_seeding_date"));
            crop.setDateToHarvest(resultSet.getDate("crop_harvesting_date"));
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
