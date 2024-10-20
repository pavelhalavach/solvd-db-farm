package com.solvd.laba.model;

import lombok.*;

import java.util.Objects;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Owner {
    private int id;
    private String firstName;
    private String secondName;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Owner owner = (Owner) object;
        return Objects.equals(firstName, owner.firstName) && Objects.equals(secondName, owner.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName);
    }
}
