package com.solvd.laba.jdbc.dao.impl.jdbc;

import com.solvd.laba.jdbc.dao.WorkerDAO;
import com.solvd.laba.jdbc.model.Responsibility;
import com.solvd.laba.jdbc.model.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorkerDAOImpl implements WorkerDAO {     
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String INSERT = "INSERT INTO workers(" +
            "first_name, second_name, farm_id" +
            ") VALUES (?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id), farm_id = VALUES(farm_id)";
    private static final String UPDATE = "UPDATE workers SET " +
            "first_name = ?, second_name = ?, farm_id =? " +
            "WHERE id = ?";
    private static final String DELETE_FROM_CONN_TABLE_BY_ID = "DELETE FROM worker_responsibilities " +
            "WHERE worker_id = ?";
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
            "r.profession AS role_profession " +
            "FROM workers AS w " +
            "LEFT JOIN worker_responsibilities AS wr ON w.id = wr.worker_id " +
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
            "r.profession AS role_profession " +
            "FROM workers AS w " +
            "LEFT JOIN worker_responsibilities AS wr ON w.id = wr.worker_id " +
            "LEFT JOIN responsibilities AS re ON wr.responsibility_id = re.id " +
            "LEFT JOIN roles AS r ON re.role_id = r.id " +
            "WHERE w.id = ?";

    @Override
    public void insert(Worker worker, int farmId) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1, worker.getFirstName());
            preparedStatement.setString(2, worker.getSecondName());
            preparedStatement.setInt(3, farmId);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if(resultSet.next()) {
                worker.setId(resultSet.getInt(1));
            }

            insertDataToWorkerResponsibilities(worker);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public void insertDataToWorkerResponsibilities(Worker worker){
        if (worker.getResponsibilities() != null) {
            Connection connection = connectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT IGNORE INTO worker_responsibilities " +
                    "(worker_id, responsibility_id) VALUES (?,?)"
            )) {
                for (Responsibility responsibility : worker.getResponsibilities()) {
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
    public void update(Worker worker, int farmId) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setString(1, worker.getFirstName());
            preparedStatement.setString(2, worker.getSecondName());
            preparedStatement.setInt(3, farmId);
            preparedStatement.setInt(4, worker.getId());
            preparedStatement.executeUpdate();

            deleteDataFromWorkerResponsibilities(worker);
            insertDataToWorkerResponsibilities(worker);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    public void deleteDataFromWorkerResponsibilities(Worker worker){
        Worker dbWorker = getById(worker.getId());
        if (dbWorker.getResponsibilities() != null) {
            Connection connection = connectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_CONN_TABLE_BY_ID)) {
                for (Responsibility ignored : dbWorker.getResponsibilities()) {
                    preparedStatement.setInt(1, worker.getId());
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
    public void deleteById(int id) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
//          no need to delete from worker_responsibilities because of ON DELETE CASCADE
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public Worker getById(int id) {
        Worker worker = null;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                worker = mapWorker(resultSet);
            }
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
        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)){
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
        int workerId = resultSet.getInt("worker_id");
        if (workerId > 0){
            worker = new Worker();
            worker.setId(workerId);
            worker.setFirstName(resultSet.getString("worker_first_name"));
            worker.setSecondName(resultSet.getString("worker_second_name"));
            worker.setResponsibilities(new ArrayList<>());
            do {
                int responsibilityId = resultSet.getInt("responsibility_id");
                if (responsibilityId > 0)   {
                    worker.getResponsibilities().add(ResponsibilityDAOImpl.mapResponsibility(resultSet));
                }
            } while (resultSet.next() && resultSet.getInt("worker_id") == worker.getId());
            resultSet.previous();
        }
        return worker;
    }

    public static List<Worker> mapWorkers(ResultSet resultSet) throws SQLException {
        List<Worker> workers = new ArrayList<>();
        while (resultSet.next()){
            workers.add(mapWorker(resultSet));
        };
        return workers;
    }
}

