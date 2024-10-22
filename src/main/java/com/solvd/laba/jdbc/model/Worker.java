package com.solvd.laba.jdbc.model;

import lombok.Data;

import java.util.List;

@Data public class Worker {
    private int id;
    private String firstName;
    private String secondName;
    private List<Responsibility> responsibilities;
}
