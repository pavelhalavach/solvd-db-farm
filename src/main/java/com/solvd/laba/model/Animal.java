package com.solvd.laba.model;

import lombok.Data;
import lombok.NonNull;

@Data public class Animal {
    @NonNull private Integer id;
    @NonNull private String name;
}
