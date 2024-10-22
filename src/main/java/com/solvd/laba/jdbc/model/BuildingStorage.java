package com.solvd.laba.jdbc.model;

import lombok.Data;

@Data public class BuildingStorage<T> {
    private int id;
    private int quantity;
    private T item;
}
