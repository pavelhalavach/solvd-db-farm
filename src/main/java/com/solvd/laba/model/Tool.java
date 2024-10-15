package com.solvd.laba.model;

import lombok.Data;
import lombok.NonNull;

@Data public class Tool {
    @NonNull private Integer id;
    @NonNull private String name;
    @NonNull private String brand;
}
