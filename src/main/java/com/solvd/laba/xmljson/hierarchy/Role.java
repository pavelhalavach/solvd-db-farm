package com.solvd.laba.xmljson.hierarchy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.solvd.laba.xmljson.xml.Node;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

//@XmlRootElement(name = "role", namespace = "http://www.example.com/farm")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({ "role_id", "profession"})
@Data public class Role {
    @XmlAttribute
    @JsonProperty(value = "roleId")
    private int id;
    @XmlElement(name = "profession")
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
