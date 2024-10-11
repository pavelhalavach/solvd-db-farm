package com.solvd.laba.model;

import lombok.Data;
import lombok.NonNull;

@Data public class BuildingType {
    @NonNull private Integer id;
    @NonNull private String type;
}
