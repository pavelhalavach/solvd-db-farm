package com.solvd.laba.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Responsibility {
    @NonNull private Integer id;
    @NonNull private String task;
    @NonNull private Role role;
    private String description;
}
