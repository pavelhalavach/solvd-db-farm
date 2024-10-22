package com.solvd.laba.jdbc.model;

import lombok.Data;

@Data public class Responsibility {
    private int id;
    private String task;
    private Role role;
    private String description;
}
