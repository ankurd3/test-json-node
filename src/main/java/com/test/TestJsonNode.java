package com.test;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class TestJsonNode {

    public static void main(String[] args) {
        System.out.println("asas");
        try{
            //readJsonWithObjectMapper();
            //readPhoneNumbers();
            //testStr();
            testSkipAd1();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readJsonWithObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<?,?> empMap = objectMapper.readValue(new FileInputStream("/Users/ankurd3/Downloads/TestJsonNode/src/main/resources/employee.json"),Map.class);
        for (Map.Entry<?, ?> entry : empMap.entrySet())
        {
            System.out.println("\n----------------------------\n"+entry.getKey() + "=" + entry.getValue()+"\n");
        }
    }

    public static Iterator<JsonNode> readPhoneNumbers() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File("/Users/ankurd3/Downloads/TestJsonNode/src/main/resources/employee.json"));
        JsonNode phoneNumbersNode = rootNode.path("phoneNumbers");
        Iterator<JsonNode> elements = phoneNumbersNode.elements();
        while(elements.hasNext()){
            JsonNode phoneNode = elements.next();
            System.out.println("\n----------------------------\nPhone Numbers = "+phoneNode.asLong());
        }
        return elements;
    }

    public static void testStr() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File("/Users/ankurd3/Downloads/TestJsonNode/src/main/resources/employee.json"));

        JsonNode street1=rootNode.path("address").path("street");
        System.out.println("1st : "+street1.asText());

        JsonNode street2=rootNode.path("address").path("ad").path("street");
        System.out.println("2nd : "+street2.asText());

        JsonNode street3=rootNode.path("address").path("ad").path("a").path("street");
        System.out.println("3rd : "+street3.asText());
    }

    public static void testSkipAd() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File("/Users/ankurd3/Downloads/TestJsonNode/src/main/resources/employee.json"));

        JsonNode el=rootNode.at("/address");

        String n = objectMapper.writeValueAsString(el);

        Map<?,?> nData= objectMapper.readValue(n, Map.class);

        for (Map.Entry<?, ?> entry : nData.entrySet())
        {
            System.out.println("\n----------------------------\n"+entry.getKey() + "=" + entry.getValue()+"\n");
            if (entry.getValue() != null && entry.getValue() instanceof Map){
                JsonNode nl = objectMapper.readTree(objectMapper.writeValueAsString(entry.getValue()));
                if (!nl.isMissingNode()){
                    JsonNode v = nl.at("/street");
                    if (!v.isMissingNode()){
                        System.out.println(v.asText());
                    }
                }
            }
        }


    }

    public static void testSkipAd1() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File("/Users/ankurd3/Downloads/TestJsonNode/src/main/resources/employee.json"));

        JsonNode el=rootNode.at("/address");

        String n = objectMapper.writeValueAsString(el);

        Map<?,?> nData= objectMapper.readValue(n, Map.class);

        for (Map.Entry<?, ?> entry : nData.entrySet())
        {
            System.out.println("\n----------------------------\n"+entry.getKey() + "=" + entry.getValue()+"\n");
            if (entry.getValue() != null && entry.getValue() instanceof Map){
                JsonNode nl = objectMapper.readTree(objectMapper.writeValueAsString(entry.getValue()));
                if (!nl.isMissingNode()){
                    JsonNode v = nl.at("/street");
                    if (!v.isMissingNode()){
                        System.out.println(v.asText());
                    }
                }
            }
        }

        nData.entrySet()
            .stream()
            .forEach(e -> e.getValue() instanceof  Map);
    }
}
