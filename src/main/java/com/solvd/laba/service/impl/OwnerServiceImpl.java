package com.solvd.laba.service.impl;

import com.solvd.laba.dao.OwnerDAO;
import com.solvd.laba.dao.impl.OwnerDAOImpl;
import com.solvd.laba.model.Owner;
import com.solvd.laba.service.OwnerService;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {

    private final OwnerDAO ownerDAO;

    public OwnerServiceImpl() {
        this.ownerDAO = new OwnerDAOImpl();
    }

    @Override
    public void insert(Owner owner) {
        ownerDAO.insert(owner);
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
