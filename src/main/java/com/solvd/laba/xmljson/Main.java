package com.solvd.laba.xmljson;

import com.solvd.laba.xmljson.hierarchy.Farm;
import com.solvd.laba.xmljson.json.JsonJACKSONConverter;
import com.solvd.laba.xmljson.xml.Node;
import com.solvd.laba.xmljson.xml.XmlDOMParser;
import com.solvd.laba.xmljson.xml.XmlJAXBConverter;

import java.io.File;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        File farmXml = new File("src/main/resources/farm.xml");
        Node node = XmlDOMParser.parse(farmXml);

        Farm farmFromXmlDOM = new Farm();
        farmFromXmlDOM.mapFarm(node);
        System.out.println("farmFromXmlDOM:");
        System.out.println(farmFromXmlDOM);

        XmlJAXBConverter.marshal(farmFromXmlDOM, "src/main/resources/farm_generated.xml");

        System.out.println("farmFromXmlJAXB:");
        Farm farmFromXmlJAXB = XmlJAXBConverter
                .unmarshal(
                        "src/main/resources/farm_generated.xml",
                        "src/main/resources/farm.xsd",
                        Farm.class);
        System.out.println(farmFromXmlJAXB);

        JsonJACKSONConverter.serializeToJson("src/main/resources/farm.json", farmFromXmlJAXB);
        Farm farmFromJson = JsonJACKSONConverter.parseFromJson("src/main/resources/farm.json", Farm.class);

        System.out.println("farmFromXmlDom equals farmFromJson: ");
        System.out.println(Objects.equals(farmFromXmlDOM, farmFromJson));
    }


}
