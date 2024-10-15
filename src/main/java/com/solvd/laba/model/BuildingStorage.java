package com.solvd.laba.model;

import lombok.Data;
import lombok.NonNull;

@Data public class BuildingStorage<T> {
    @NonNull private Integer id;
    @NonNull private int quantity;
    @NonNull private T item;
}
