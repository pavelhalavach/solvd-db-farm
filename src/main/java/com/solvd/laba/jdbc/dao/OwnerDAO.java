package com.solvd.laba.jdbc.dao;

import com.solvd.laba.jdbc.model.Crop;
import com.solvd.laba.jdbc.model.Owner;

import java.util.List;

public interface OwnerDAO {
    void insert(Owner owner);
    void update(Owner owner);
    void deleteById(int id);
    Owner getById(int id);
    List<Owner> getAll();
}
