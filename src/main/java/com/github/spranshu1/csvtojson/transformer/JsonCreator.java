package com.github.spranshu1.csvtojson.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonCreator {

    @Autowired
    private ObjectMapper mapper;

    public ObjectNode createJsonTemplate(Row headers){
        ObjectNode root = mapper.createObjectNode();

        String firstCell = headers.getCell(0).toString();

        String entity = firstCell.substring(0,firstCell.indexOf("_"));





        return null;
    }


}
