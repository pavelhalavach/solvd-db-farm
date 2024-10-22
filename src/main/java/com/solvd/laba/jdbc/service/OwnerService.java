package com.solvd.laba.jdbc.service;

import com.solvd.laba.jdbc.model.Owner;

import java.util.List;

public interface OwnerService {
    void insert(Owner owner);
    void update(Owner owner);
    void deleteById(int id);
    Owner getById(int id);
    List<Owner> getAll();
}
