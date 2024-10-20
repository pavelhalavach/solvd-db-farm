package com.solvd.laba.service;

import com.solvd.laba.model.Role;

import java.util.List;

public interface RoleService {
    void insert(Role role);

    void update(Role role);

    void deleteByProfession(String profession);
    Role getById(int id);
    Role getByProfession(String profession);
    List<Role> getAll();
}
