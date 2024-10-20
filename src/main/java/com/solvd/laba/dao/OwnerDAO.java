package com.solvd.laba.dao;

import com.solvd.laba.model.Crop;
import com.solvd.laba.model.Owner;

import java.util.List;

public interface OwnerDAO {
    void insert(Owner owner);
    void update(Owner owner);
    void deleteById(int id);
    Owner getById(int id);
    List<Owner> getAll();
}
