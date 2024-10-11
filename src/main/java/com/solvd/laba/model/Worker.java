package com.solvd.laba.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data public class Worker {
    @NonNull private Integer id;
    @NonNull private String firstName;
    @NonNull private String secondName;
    @NonNull private List<Responsibility> responsibilities;
}
