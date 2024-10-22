package com.solvd.laba.xml.hierarchy;

import com.solvd.laba.xml.Node;
import lombok.Data;

import java.util.ArrayList;

@Data public class Responsibility {
    private int id;
    private String task;
    private Role role;
    private String description;

    public void mapResponsibility(Node node){
        id = Integer.parseInt(node.getAttributes().get("id").replaceAll("\"", ""));
        for (Node child : node.getChildren()) {
            if (child.getNameTag().equals("task")) {
                task = child.getText();
            }
            if (child.getNameTag().equals("description")) {
                description = child.getText();
            }
            if (child.getNameTag().equals("role")){
                role = new Role();
                role.mapRole(child);
            }
        }

    }
}
