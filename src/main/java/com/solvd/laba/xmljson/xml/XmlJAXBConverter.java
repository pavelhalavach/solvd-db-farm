package com.solvd.laba.xmljson.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlJAXBConverter {


    public static <T> void marshal(T obj, String fileName) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(obj, new File(fileName));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T unmarshal(String xmlPath, String xsdPath, Class<T> objClass) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        JAXBContext context = null;
        try {
            schema = schemaFactory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            System.out.println("XSD schema is checked");

            context = JAXBContext.newInstance(objClass);
            return objClass.cast(context.createUnmarshaller().unmarshal(new File(xmlPath)));
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            System.out.println("XLM does not fit XSD schema");
        }
        return null;
    }


}
