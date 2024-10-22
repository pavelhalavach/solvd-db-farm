package com.solvd.laba.xml.hierarchy;

import com.solvd.laba.xml.Node;
import lombok.*;

import java.util.Objects;

@Data public class Owner {
    private int id;
    private String firstName;
    private String secondName;

    public void mapOwner(Node node) {
        id = Integer.parseInt(node.getAttributes().get("id").replaceAll("\"", ""));
        for (Node child : node.getChildren()) {
            if (child.getNameTag().equals("first_name")) {
                firstName = child.getText();
            }
            if (child.getNameTag().equals("second_name")) {
                secondName = child.getText();
            }
        }
    }
}
