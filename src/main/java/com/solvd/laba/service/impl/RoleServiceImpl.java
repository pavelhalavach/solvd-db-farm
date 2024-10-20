package com.solvd.laba.service.impl;

import com.solvd.laba.dao.RoleDAO;
import com.solvd.laba.dao.impl.RoleDAOImpl;
import com.solvd.laba.model.Role;
import com.solvd.laba.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    public RoleServiceImpl() {
        this.roleDAO = new RoleDAOImpl();
    }

    @Override
    public void insert(Role role) {
        roleDAO.insert(role);
    }

    @Override
    public void update(Role role){
        roleDAO.update(role);
    }

    @Override
    public void deleteByProfession(String profession) {
        roleDAO.deleteByProfession(profession);
    }

    @Override
    public Role getById(int id) throws NullPointerException{
        return roleDAO.getById(id);
    }

    @Override
    public Role getByProfession(String profession) {
        return roleDAO.getByProfession(profession);
    }

    @Override
    public List<Role> getAll() throws NullPointerException{
        return roleDAO.getAll();
    }
}
