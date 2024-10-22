package com.solvd.laba.xml.hierarchy;

import com.solvd.laba.xml.Node;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data public class Farm {
    private int id;
    private String name;
    private String location;
    private Owner owner;
//    private List<Field> fields;
    private List<Worker> workers;
//    private List<Building> buildings;

    public void mapFarm(Node node){
        id = Integer.parseInt(node.getAttributes().get("id").replaceAll("\"",""));

        for (Node child : node.getChildren()){
            if (child.getNameTag().equals("name")){
                name = child.getText();
            }
            if (child.getNameTag().equals("location")){
                location = child.getText();
            }
            if (child.getNameTag().equals("owner")){
                owner = new Owner();
                owner.mapOwner(child);
            }
            if (child.getNameTag().equals("workers")){
                workers = new ArrayList<>();
                child.getChildren().forEach(node2-> {
                    Worker worker = new Worker();
                    worker.mapWorker(node2);
                    workers.add(worker);
                });
            }
        }
//        name = node.getChildren().stream().filter(node1 -> Objects.equals(node1.getNameTag(), "name")).toString();
//        owner = (Owner) node.getChildren().stream().filter(node1 -> Objects.equals(node1.getNameTag(), "owner"));
//        workers.add(node.getChildren().stream().filter(node1 -> Objects.equals(node1.getNameTag(), "workers")))
//        workers = node.getChildren().stream()
//                .filter(node1 -> Objects.equals(node1.getNameTag(), "workers"))
//                .filter(node2 -> node2.getChildren()).forEach(worker -> worker.ge);
    }
}
