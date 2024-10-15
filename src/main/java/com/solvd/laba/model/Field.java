package com.solvd.laba.model;

import lombok.Data;
import lombok.NonNull;

@Data public class Field {
    @NonNull private Integer id;
    @NonNull private float areaInAcres;
    @NonNull private String coordinates;
    @NonNull private Crop crop;
}
