package com.solvd.laba.xml.hierarchy;

import com.solvd.laba.xml.Node;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data public class Worker {
    private int id;
    private String firstName;
    private String secondName;
    private List<Responsibility> responsibilities;

    public void mapWorker(Node node){
        id = Integer.parseInt(node.getAttributes().get("id").replaceAll("\"", ""));
        for (Node child : node.getChildren()) {
            if (child.getNameTag().equals("first_name")) {
                firstName = child.getText();
            }
            if (child.getNameTag().equals("second_name")) {
                secondName = child.getText();
            }
            if (child.getNameTag().equals("responsibilities")){
                responsibilities = new ArrayList<>();
                child.getChildren().forEach(node2-> {
                    Responsibility responsibility = new Responsibility();
                    responsibility.mapResponsibility(node2);
                    responsibilities.add(responsibility);
                });
            }
        }

    }
}
