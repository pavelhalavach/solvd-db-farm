package com.solvd.laba.xmljson.hierarchy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.solvd.laba.xmljson.xml.Node;
import jakarta.xml.bind.annotation.*;
import lombok.*;

//@XmlRootElement(name = "owner", namespace = "http://www.example.com/farm")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({ "owner_id", "firstName", "secondName"})
@Data public class Owner {
    @XmlAttribute
    @JsonProperty(value = "ownerId")
    private int id;
    @XmlElement(name = "first_name")
    private String firstName;
    @XmlElement(name = "second_name")
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
