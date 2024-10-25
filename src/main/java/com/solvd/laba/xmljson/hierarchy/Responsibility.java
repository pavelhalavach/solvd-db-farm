package com.solvd.laba.xmljson.hierarchy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.solvd.laba.xmljson.xml.Node;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

//@XmlRootElement(name = "responsibility", namespace = "http://www.example.com/farm")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({ "responsibility_id", "task", "description", "role" })
@Data public class Responsibility {
    @XmlAttribute
    @JsonProperty(value = "responsibilityId")
    private int id;
    @XmlElement(name = "task")
    private String task;
    @XmlElement(name = "description")
    private String description;
    @XmlElement(name = "role")
    private Role role;


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
