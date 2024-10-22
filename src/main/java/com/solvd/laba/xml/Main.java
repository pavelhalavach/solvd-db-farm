package com.solvd.laba.xml;

import com.solvd.laba.xml.hierarchy.Farm;

import java.io.File;

public class Main {
    public static void main(String[] args){
        File farmFile = new File("src/main/resources/farm.xml");
        Node node = XmlDOMParser.parse(farmFile);

        System.out.println(node);
        Farm farm = new Farm();
        farm.mapFarm(node);
        System.out.println(farm);
    }


}
