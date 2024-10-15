package com.solvd.laba.service;

import com.solvd.laba.model.Role;

import java.util.List;

public interface RoleService {
    void insert(Role role);
    void deleteByProfession(String profession);
    Role getById(int id);
    List<Role> getAll();
}
