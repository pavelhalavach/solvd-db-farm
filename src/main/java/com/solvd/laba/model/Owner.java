package com.solvd.laba.model;

import lombok.Data;
import lombok.NonNull;

@Data public class Owner {
    @NonNull
    private Integer id;
    @NonNull
    private String firstName;
    @NonNull
    private String secondName;
}
