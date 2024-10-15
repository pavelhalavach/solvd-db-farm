package com.solvd.laba.model;

import lombok.Data;

@Data public class Responsibility {
    private int id;
    private String task;
    private Role role;
    private String description;
}
