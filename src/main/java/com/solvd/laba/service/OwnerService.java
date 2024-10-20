package com.solvd.laba.service;

import com.solvd.laba.model.Owner;

import java.util.List;

public interface OwnerService {
    void insert(Owner owner);
    void update(Owner owner);
    void deleteById(int id);
    Owner getById(int id);
    List<Owner> getAll();
}
