package com.solvd.laba.jdbc.model;

import lombok.Data;

@Data public class Field {
    private int id;
    private float areaInAcres;
    private String coordinates;
    private Crop crop;
}
