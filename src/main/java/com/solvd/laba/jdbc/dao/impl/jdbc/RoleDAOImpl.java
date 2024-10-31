package com.solvd.laba.jdbc.dao.impl.jdbc;

import com.solvd.laba.jdbc.dao.RoleDAO;
import com.solvd.laba.jdbc.model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String INSERT = "INSERT INTO roles (profession) VALUES (?) " +
            "ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id)";
    private static final String UPDATE = "UPDATE roles SET profession = ? " +
            "WHERE id = ?";
    private static final String DELETE_BY_PROFESSION = "DELETE FROM roles WHERE profession = ?";
    private static final String SELECT_ALL = "SELECT id AS role_id, profession as role_profession FROM roles " +
            "ORDER BY id";
    private static final String SELECT_BY_ID = "SELECT " +
            "id AS role_id, " +
            "profession AS role_profession " +
            "FROM roles " +
            "WHERE id = ?";

    private static final String SELECT_BY_PROFESSION = "SELECT " +
            "id AS role_id, " +
            "profession AS role_profession " +
            "FROM roles " +
            "WHERE profession = ?";

    @Override
    public void insert(Role role) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, role.getProfession());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");

            if (resultSet.next()) {
                role.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection();
        }
    }

    @Override
    public void update(Role role) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setString(1, role.getProfession());
            preparedStatement.setInt(2, role.getId());
            preparedStatement.executeUpdate();
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
        Role role = null;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                role = mapRole(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection();
        }
        return role;
    }

    @Override
    public Role getByProfession(String profession) {
        Role role = null;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_PROFESSION)){
            preparedStatement.setString(1, profession);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                role = mapRole(resultSet);
            }
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
        if (id > 0){
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
