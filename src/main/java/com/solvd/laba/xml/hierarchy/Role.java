package com.solvd.laba.xml.hierarchy;

import com.solvd.laba.xml.Node;
import lombok.Data;

@Data public class Role {
    private int id;
    private String profession;

    public void mapRole(Node node){
        id = Integer.parseInt(node.getAttributes().get("id").replaceAll("\"", ""));
        for (Node child : node.getChildren()){
            if (child.getNameTag().equals("profession")){
                profession = child.getText();
            }
        }
    }
}
