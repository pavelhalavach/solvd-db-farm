package com.solvd.laba.dao;

import com.solvd.laba.model.Role;

import java.util.List;

public interface RoleDAO {
    void insert(Role role);
    void deleteByProfession(String profession);
    Role getById(int id);
    List<Role> getAll();
}
