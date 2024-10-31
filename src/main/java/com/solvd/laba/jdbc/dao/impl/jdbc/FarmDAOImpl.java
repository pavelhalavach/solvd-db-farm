package com.solvd.laba.jdbc.dao.impl.jdbc;

import com.solvd.laba.jdbc.dao.FarmDAO;
import com.solvd.laba.jdbc.model.Farm;
import com.solvd.laba.jdbc.model.Field;
import com.solvd.laba.jdbc.model.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FarmDAOImpl implements FarmDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String INSERT = "INSERT INTO farms (" +
            "name, location, owner_id" +
            ") VALUES (?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id), owner_id = VALUES(owner_id)";
    private static final String UPDATE = "UPDATE farms SET " +
            "name = ?, location = ?, owner_id = ? " +
            "WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM farms " +
            "WHERE id = ?";
    private static final String SELECT_ALL = "SELECT " +
            "f.id AS farm_id, " +
            "f.name AS farm_name, " +
            "f.location AS farm_location, " +
            "f.owner_id AS owner_id, " +
            "o.first_name AS owner_first_name, " +
            "o.second_name AS owner_second_name, " +
            "fi.id AS field_id, " +
            "fi.area_in_acres AS field_area_in_acres, " +
            "fi.coordinates AS field_coordinates, " +
            "fi.crop_id AS crop_id, " +
            "c.name AS crop_name, " +
            "c.date_to_seed AS crop_seeding_date, " +
            "c.date_to_harvest AS crop_harvesting_date, " +
            "w.id AS worker_id, " +
            "w.first_name AS worker_first_name, " +
            "w.second_name AS worker_second_name, " +
            "wr.responsibility_id AS responsibility_id, " +
            "re.task AS responsibility_task, " +
            "re.description AS responsibility_description, " +
            "re.role_id AS role_id, " +
            "r.profession AS role_profession " +
            "FROM farms AS f " +
            "LEFT JOIN owners AS o on f.owner_id = o.id " +
            "LEFT JOIN fields AS fi on f.id = fi.farm_id " +
            "LEFT JOIN crops AS c ON fi.crop_id = c.id " +
            "LEFT JOIN workers AS w ON f.id = w.farm_id " +
            "LEFT JOIN worker_responsibilities AS wr ON w.id = wr.worker_id " +
            "LEFT JOIN responsibilities AS re ON wr.responsibility_id = re.id " +
            "LEFT JOIN roles AS r ON re.role_id = r.id";
    private static final String SELECT_BY_ID = "SELECT " +
            "f.id AS farm_id, " +
            "f.name AS farm_name, " +
            "f.location AS farm_location, " +
            "f.owner_id AS owner_id, " +
            "o.first_name AS owner_first_name, " +
            "o.second_name AS owner_second_name, " +
            "fi.id AS field_id, " +
            "fi.area_in_acres AS field_area_in_acres, " +
            "fi.coordinates AS field_coordinates, " +
            "fi.crop_id AS crop_id, " +
            "c.name AS crop_name, " +
            "c.date_to_seed AS crop_seeding_date, " +
            "c.date_to_harvest AS crop_harvesting_date, " +
            "w.id AS worker_id, " +
            "w.first_name AS worker_first_name, " +
            "w.second_name AS worker_second_name, " +
            "wr.responsibility_id AS responsibility_id, " +
            "re.task AS responsibility_task, " +
            "re.description AS responsibility_description, " +
            "re.role_id AS role_id, " +
            "r.profession AS role_profession " +
            "FROM farms AS f " +
            "LEFT JOIN owners AS o on f.owner_id = o.id " +
            "LEFT JOIN fields AS fi on f.id = fi.farm_id " +
            "LEFT JOIN crops AS c ON fi.crop_id = c.id " +
            "LEFT JOIN workers AS w ON f.id = w.farm_id " +
            "LEFT JOIN worker_responsibilities AS wr ON w.id = wr.worker_id " +
            "LEFT JOIN responsibilities AS re ON wr.responsibility_id = re.id " +
            "LEFT JOIN roles AS r ON re.role_id = r.id " +
            "WHERE f.id = ?";

    @Override
    public void insert(Farm farm) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1, farm.getName());
            preparedStatement.setString(2, farm.getLocation());
            preparedStatement.setInt(3, farm.getOwner().getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                farm.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public void update(Farm farm) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setString(1, farm.getName());
            preparedStatement.setString(2, farm.getLocation());
            preparedStatement.setInt(3, farm.getOwner().getId());
            preparedStatement.setInt(4, farm.getId());
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
    public Farm getById(int id) {
        Farm farm = null;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                farm = mapFarm(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return farm;
    }

    @Override
    public List<Farm> getAll() {
        List<Farm> farms;
        Connection connection = connectionPool.getConnection();
        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)){
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            farms = mapFarms(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return farms;
    }

    public static Farm mapFarm(ResultSet resultSet) throws SQLException {
        Farm farm = null;
        int farmId = resultSet.getInt("farm_id");
        if (farmId > 0){
            farm = new Farm();
            farm.setId(farmId);
            farm.setName(resultSet.getString("farm_name"));
            farm.setLocation(resultSet.getString("farm_location"));
            farm.setOwner(OwnerDAOImpl.mapOwner(resultSet));
            farm.setFields(new ArrayList<>());
            List<Worker> workers = new ArrayList<>();
            farm.setWorkers(workers);

            Map<Integer, Worker> workerMap = new HashMap<>();

            do {
                int fieldId = resultSet.getInt("field_id");
                Field field = FieldDAOImpl.mapField(resultSet);
                if (fieldId > 0 && !farm.getFields().contains(field))   {
                    farm.getFields().add(field);
                }

                int workerId = resultSet.getInt("worker_id");
                if (workerId > 0)   {
                    Worker worker = workerMap.get(workerId);
                    if (worker == null) {
                        worker = WorkerDAOImpl.mapWorker(resultSet);
                        farm.getWorkers().add(worker);
                        workerMap.put(workerId, worker);
                    }
                }
            } while (resultSet.next() && resultSet.getInt("farm_id") == farm.getId());
            resultSet.previous();
        }
        return farm;
    }

    public static List<Farm> mapFarms(ResultSet resultSet) throws SQLException {
        List<Farm> farms = new ArrayList<>();
        while (resultSet.next()){
            farms.add(mapFarm(resultSet));
        };
        return farms;
    }
}
