package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.ConnectionPool;
import com.solvd.laba.dao.FieldDAO;
import com.solvd.laba.model.Field;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FieldDAOImpl implements FieldDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String INSERT = "INSERT INTO fields (" +
            "area_in_acres, coordinates, farm_id, crop_id" +
            ") " +
            "VALUES (?,?,?,?) " +
            "ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id), farm_id = VALUES(farm_id), crop_id = VALUES(crop_id)";
    private static final String DELETE_BY_ID = "DELETE FROM fields " +
            "WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM fields ORDER BY id";
    private static final String SELECT_BY_ID = "SELECT " +
            "f.id AS field_id, " +
            "f.area_in_acres AS field_area_in_acres, " +
            "f.coordinates AS field_coordinates, " +
            "f.farm_id AS farm_id, " +
            "f.crop_id AS crop_id, " +
            "c.name AS crop_name, " +
            "c.date_to_seed AS crop_seeding_date, " +
            "c.date_to_harvest AS crop_harvesting_date " +
            "FROM fields AS f " +
            "LEFT JOIN crops AS c ON crop_id = c.id " +
            "WHERE f.id = ? " +
            "ORDER BY f.id";

    private static final String SELECT_BY_FARM_ID = "SELECT " +
            "f.id AS field_id, " +
            "f.area_in_acres AS field_area_in_acres, " +
            "f.coordinates AS field_coordinates, " +
            "f.farm_id AS farm_id, " +
            "f.crop_id AS crop_id, " +
            "c.name AS crop_name, " +
            "c.date_to_seed AS crop_seeding_date, " +
            "c.date_to_harvest AS crop_harvesting_date " +
            "FROM fields AS f " +
            "LEFT JOIN crops AS c ON f.crop_id = c.id " +
            "WHERE farm_id = ?";

    @Override
    public void insert(Field field, int farmId) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setDouble(1, field.getAreaInAcres());
            preparedStatement.setString(2, field.getCoordinates());
            preparedStatement.setInt(3, farmId);
            preparedStatement.setInt(4, field.getCrop().getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                field.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public void deleteById(int id){
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
    public Field getById(int id) {
        Field field = null;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                field = mapField(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return field;
    }

    @Override
    public List<Field> getAllByFarmId(int farmId) {
        List<Field> fields;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_FARM_ID)){
            preparedStatement.setInt(1, farmId);
            ResultSet resultSet = preparedStatement.executeQuery();
            fields = mapFields(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return fields;
    }

    public static Field mapField(ResultSet resultSet) throws SQLException {
        Field field = null;
        int id = resultSet.getInt("field_id");
        if (id != 0){
            field = new Field();
            field.setId(id);
            field.setAreaInAcres(resultSet.getFloat("field_area_in_acres"));
            field.setCoordinates(resultSet.getString("field_coordinates"));
            field.setCrop(CropDAOImpl.mapCrop(resultSet));
        }
        return field;
    }

    public static List<Field> mapFields(ResultSet resultSet) throws SQLException {
        List<Field> fields = new ArrayList<>();
        while (resultSet.next()){
            fields.add(mapField(resultSet));
        }
        return fields;
    }
}
