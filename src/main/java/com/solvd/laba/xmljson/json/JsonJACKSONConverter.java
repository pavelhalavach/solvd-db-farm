package com.solvd.laba.xmljson.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.laba.xmljson.hierarchy.Farm;

import java.io.File;
import java.io.IOException;

public class JsonJACKSONConverter {
    public static <T> void serializeToJson(String newFilePath, T obj){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Farm farmFromJson = null;
        try {
            mapper.writeValue(new File(newFilePath), obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static  <T> T parseFromJson(String jsonFilePath, Class<T> objClass){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        try {
            return objClass.cast(mapper.readValue(new File(jsonFilePath), objClass));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
