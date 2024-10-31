package com.solvd.laba.jdbc.service.impl;

import com.solvd.laba.jdbc.dao.OwnerDAO;
import com.solvd.laba.jdbc.dao.impl.jdbc.OwnerDAOImpl;
import com.solvd.laba.jdbc.dao.impl.mybatis.OwnerMapperImpl;
import com.solvd.laba.jdbc.model.Owner;
import com.solvd.laba.jdbc.service.OwnerService;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {

    private final OwnerDAO ownerDAO;

    public OwnerServiceImpl() {
//        this.ownerDAO = new OwnerDAOImpl();
        this.ownerDAO = new OwnerMapperImpl();
    }

    @Override
    public void insert(Owner owner) {
        ownerDAO.insert(owner);
    }

    @Override
    public void update(Owner owner) {
        ownerDAO.update(owner);
    }

    @Override
    public void deleteById(int id) {
        ownerDAO.deleteById(id);
    }

    @Override
    public Owner getById(int id) throws NullPointerException{
        return ownerDAO.getById(id);
    }

    @Override
    public List<Owner> getAll() throws NullPointerException{
        return ownerDAO.getAll();
    }
}
