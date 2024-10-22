package com.solvd.laba.jdbc.dao.impl;

import com.solvd.laba.jdbc.dao.ConnectionPool;
import com.solvd.laba.jdbc.dao.ResponsibilityDAO;
import com.solvd.laba.jdbc.model.Responsibility;
import com.solvd.laba.jdbc.model.Worker;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResponsibilityDAOImpl implements ResponsibilityDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String INSERT = "INSERT INTO responsibilities (task, description, role_id) VALUES (?,?,?) " +
            "ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id)";
    private static final String UPDATE = "UPDATE responsibilities " +
            "SET task = ?, description = ?, role_id = ? " +
            "WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM responsibilities WHERE id = ?";
    private static final String SELECT_ALL = "SELECT " +
            "re.id AS responsibility_id, " +
            "re.task as responsibility_task, " +
            "re.description AS responsibility_description, " +
            "re.role_id AS role_id, " +
            "r.profession AS role_profession " +
            "FROM responsibilities AS re " +
            "LEFT JOIN roles AS r ON role_id = r.id " +
            "ORDER BY re.id";
    private static final String SELECT_BY_ID = "SELECT " +
            "re.id AS responsibility_id, " +
            "re.task as responsibility_task, " +
            "re.description AS responsibility_description, " +
            "re.role_id AS role_id, " +
            "r.profession AS role_profession " +
            "FROM responsibilities AS re " +
            "LEFT JOIN roles AS r ON role_id = r.id " +
            "WHERE re.id = ?";
    
    @Override
    public void insert(Responsibility responsibility) {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                preparedStatement.setString(1, responsibility.getTask());
                preparedStatement.setString(2, responsibility.getDescription());
                preparedStatement.setInt(3, responsibility.getRole().getId());

                preparedStatement.executeUpdate();

                ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
                if (resultSet.next()) {
                    responsibility.setId(resultSet.getInt(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                connectionPool.releaseConnection();
            }
    }

    @Override
    public void update(Responsibility responsibility) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setString(1, responsibility.getTask());
            preparedStatement.setString(2, responsibility.getDescription());
            preparedStatement.setInt(3, responsibility.getRole().getId());
            preparedStatement.setInt(4, responsibility.getId());
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
    public Responsibility getById(int id) {
        Responsibility responsibility = null;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                responsibility = mapResponsibility(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return responsibility;
    }

    @Override
    public List<Responsibility> getAll() {
        List<Responsibility> responsibilities;
        Connection connection = connectionPool.getConnection();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            responsibilities = mapResponsibilities(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return responsibilities;
    }

    public static Responsibility mapResponsibility(ResultSet resultSet) throws SQLException {
        Responsibility responsibility = null;
        int id = resultSet.getInt("responsibility_id");
        if (id != 0){
            responsibility = new Responsibility();
            responsibility.setId(id);
            responsibility.setTask(resultSet.getString("responsibility_task"));
            responsibility.setDescription(resultSet.getString("responsibility_description"));
            responsibility.setRole(RoleDAOImpl.mapRole(resultSet));
        }
        return responsibility;
    }

    public static List<Responsibility> mapResponsibilities(ResultSet resultSet) throws SQLException {
        List<Responsibility> responsibilities = new ArrayList<>();
        while (resultSet.next()){
            responsibilities.add(mapResponsibility(resultSet));
        }
        return responsibilities;
    }
}

