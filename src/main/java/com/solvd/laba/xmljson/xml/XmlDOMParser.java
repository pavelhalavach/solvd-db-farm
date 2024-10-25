package com.solvd.laba.xmljson.xml;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

public class XmlDOMParser {
    private static int index;

    public static Node parse(File xmlFile){
//        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//        Schema schema = null;
//        try {
//            schema = factory.newSchema(xsdFile);
//            Validator validator = schema.newValidator();
//            validator.validate(new StreamSource(xmlFile));
//        } catch (SAXException | IOException e) {
//            throw new RuntimeException(e);
//        }
        String xml = null;
        try {
            xml = FileUtils.readFileToString(xmlFile, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        index = 0;
        if (xml.charAt(1) == '?') {
            index = xml.indexOf('<', 1);
        }

        return parseNode(xml);
    }

    private static Node parseNode(String xml){


        Node node = new Node();
        node.setAttributes(new HashMap<>());
        node.setChildren(new ArrayList<>());
        node.setNameTag(getNameTagAndAttributes(xml, node));
        node.setText(getTextOrChild(xml, node));

        return node;

    }

    private static String getNameTagAndAttributes(String xml, Node node){
        int start = xml.indexOf('<', index)+1;
        int end = xml.indexOf('>', index);
        String element = xml.substring(start, end);
        if (element.contains("=")){
            String[] arr = element.split(" ");
            index = start + arr[0].length() + 1;
            getAttributes(xml, node);
            return arr[0];
        } else {
            index = end + 1;
            return element;
        }
    }

    private static void getAttributes(String xml, Node node){
        int end = xml.indexOf('>', index);
        String[] arr = xml.substring(index, end).split(" ");
        for (String kv : arr){
            String[] attr = kv.split("=");
            node.getAttributes().put(attr[0], attr[1]);
        }
        index = end + 1;
    }

    private static String getTextOrChild(String xml, Node node){
        String xmlWithoutWhihespaces = StringUtils.deleteWhitespace(xml.substring(index));
        if (xmlWithoutWhihespaces.charAt(0) != '<'){
            int end = xml.indexOf('<', index);
            String text = xml.substring(index, end);
            index = xml.indexOf('>', end)+1;
            return text;
        } else {
            while (xmlWithoutWhihespaces.charAt(1) != '/') {
                node.getChildren().add(parseNode(xml));
                xmlWithoutWhihespaces = StringUtils.deleteWhitespace(xml.substring(index));
                if (xmlWithoutWhihespaces.isEmpty()) return null;
                if (xmlWithoutWhihespaces.charAt(0) == '<' && xmlWithoutWhihespaces.charAt(1) == '/') {
                    index = xml.indexOf('>', index)+1;
                    return null;
                }
            }
        }
        return null;
    }
}
