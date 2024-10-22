package com.solvd.laba.jdbc.dao;

import com.solvd.laba.jdbc.model.Role;

import java.util.List;

public interface RoleDAO {
    void insert(Role role);

    void update(Role role);

    void deleteByProfession(String profession);
    Role getById(int id);

    Role getByProfession(String profession);

    List<Role> getAll();
}
