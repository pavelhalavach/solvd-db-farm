package com.solvd.laba.xmljson.hierarchy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.laba.xmljson.xml.Node;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "farm")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName(value = "farm")
@JsonPropertyOrder({ "farm_id", "name", "location", "owner", "workers" })
@Data public class Farm {
    @XmlAttribute(name = "id")
    @JsonProperty(value = "farmId")
    private int id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "location")
    private String location;
    @XmlElement(name = "owner")
    private Owner owner;
    @XmlElementWrapper(name = "workers")
    @XmlElement(name = "worker")
    private List<Worker> workers;


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


