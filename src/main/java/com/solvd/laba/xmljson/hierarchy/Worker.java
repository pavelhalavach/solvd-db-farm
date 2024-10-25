package com.solvd.laba.xmljson.hierarchy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.solvd.laba.xmljson.xml.Node;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//@XmlRootElement(name = "worker", namespace = "http://www.example.com/farm")
@JsonPropertyOrder({ "worker_id", "firstName", "secondName", "responsibilities" })
@XmlAccessorType(XmlAccessType.FIELD)
@Data public class Worker {
    @XmlAttribute
    @JsonProperty(value = "workerId")
    private int id;
    @XmlElement(name = "first_name")
    private String firstName;
    @XmlElement(name = "second_name")
    private String secondName;
    @XmlElementWrapper(name = "responsibilities")
    @XmlElement(name = "responsibility")
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
