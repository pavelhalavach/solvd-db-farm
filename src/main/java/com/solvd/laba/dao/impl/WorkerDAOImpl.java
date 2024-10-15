package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.ConnectionPool;
import com.solvd.laba.dao.WorkerDAO;
import com.solvd.laba.model.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAOImpl implements WorkerDAO {     
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String INSERT = "INSERT INTO workers(" +
            "first_name, second_name, farm_id" +
            ") VALUES (?, ?, ?)";
    private static final String DELETE_BY_ID = "DELETE FROM workers " +
            "WHERE id = ?";
    private static final String SELECT_ALL = "SELECT " +
            "w.id AS worker_id, " +
            "w.first_name AS worker_first_name, " +
            "w.second_name AS worker_second_name, " +
            "w.farm_id AS farm_id, " +
            "wr.responsibility_id AS responsibility_id, " +
            "re.task AS responsibility_task, " +
            "re.description AS responsibility_description, " +
            "re.role_id AS role_id, " +
            "r.profession AS role_profession, " +
            "FROM workers AS w " +
            "LEFT JOIN worker_responsibilities AS wr ON worker_id = wr.worker_id " +
            "LEFT JOIN responsibilities AS re ON wr.responsibility_id = re.id " +
            "LEFT JOIN roles AS r ON re.role_id = r.id";
    private static final String SELECT_BY_ID = "SELECT " +
            "w.id AS worker_id, " +
            "w.first_name AS worker_first_name, " +
            "w.second_name AS worker_second_name, " +
            "w.farm_id AS farm_id, " +
            "wr.responsibility_id AS responsibility_id, " +
            "re.task AS responsibility_task, " +
            "re.description AS responsibility_description, " +
            "re.role_id AS role_id, " +
            "r.profession AS role_profession, " +
            "FROM workers AS w " +
            "LEFT JOIN worker_responsibilities AS wr ON worker_id = wr.worker_id AND worker_id = ? " +
            "LEFT JOIN responsibilities AS re ON wr.responsibility_id = re.id " +
            "LEFT JOIN roles AS r ON re.role_id = r.id";

    @Override
    public void insert(Worker worker, int farmId) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1, worker.getFirstName());
            preparedStatement.setString(2, worker.getSecondName());
            preparedStatement.setInt(3, farmId);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM workers");
            resultSet.last();
            worker.setId(resultSet.getInt(1));

            insertDataToWorkerResponsibilities(worker);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    private void insertDataToWorkerResponsibilities(Worker worker){
        if (worker.getResponsibilities() != null) {
            Connection connection = connectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO worker_responsibilities " +
                    "(worker_id, responsibility_id) VALUES (?,?)"
            )) {
//                worker.getResponsibilities().forEach(responsibility -> {
//                    preparedStatement.setInt(1, worker.getId());
//                    preparedStatement.setInt(2, responsibility.getId());
//                    preparedStatement.executeUpdate();
//                });
                for (var responsibility : worker.getResponsibilities()) {
                    preparedStatement.setInt(1, worker.getId());
                    preparedStatement.setInt(2, responsibility.getId());
                    preparedStatement.executeUpdate();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                connectionPool.releaseConnection();
            }
        }
    }

    @Override
    public void update(Worker worker) {

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
    public Worker getById(int id) {
        Worker worker;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            worker = mapWorker(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return worker;
    }

    @Override
    public List<Worker> getAll() {
        List<Worker> workers;
        Connection connection = connectionPool.getConnection();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            workers = mapWorkers(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return workers;
    }

    public static Worker mapWorker(ResultSet resultSet) throws SQLException {
        Worker worker = null;
        int id = resultSet.getInt("worker_id");
        if (id != 0){
            worker = new Worker();
            worker.setId(id);
            worker.setFirstName(resultSet.getString("first_name"));
            worker.setSecondName(resultSet.getString("second_name"));
            worker.setResponsibilities(ResponsibilityDAOImpl.mapResponsibilities(resultSet));
        }
        return worker;
    }

    public static List<Worker> mapWorkers(ResultSet resultSet) throws SQLException {
        List<Worker> workers = new ArrayList<>();
        while (resultSet.next()){
            workers.add(mapWorker(resultSet));
        }
        return workers;
    }
}

