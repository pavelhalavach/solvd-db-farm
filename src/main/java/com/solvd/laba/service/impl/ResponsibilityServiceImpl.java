package com.solvd.laba.service.impl;

import com.solvd.laba.dao.ResponsibilityDAO;
import com.solvd.laba.dao.impl.ResponsibilityDAOImpl;
import com.solvd.laba.model.Responsibility;
import com.solvd.laba.service.ResponsibilityService;
import com.solvd.laba.service.RoleService;

import java.util.List;

public class ResponsibilityServiceImpl implements ResponsibilityService {
    private final ResponsibilityDAO responsibilityDAO;
    private final RoleService roleService;

    public ResponsibilityServiceImpl(){
        this.responsibilityDAO = new ResponsibilityDAOImpl();
        this.roleService = new RoleServiceImpl();
    }

    @Override
    public void insert(Responsibility responsibility) {
        if (responsibility.getRole() != null){
            roleService.insert(responsibility.getRole());
        }
        responsibilityDAO.insert(responsibility);
    }

    @Override
    public void update(Responsibility responsibility) {
        if (responsibility.getRole() != null){
            roleService.insert(responsibility.getRole());
        }
        responsibilityDAO.update(responsibility);
    }

    @Override
    public void deleteById(int id) {
        responsibilityDAO.deleteById(id);
    }

    @Override
    public Responsibility getById(int id) throws NullPointerException{
        return responsibilityDAO.getById(id);
    }

    @Override
    public List<Responsibility> getAll() throws NullPointerException{
        return responsibilityDAO.getAll();
    }
}
