package com.github.spranshu1.csvtojson.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.github.spranshu1.csvtojson.exceptions.InvalidFileException;

import java.io.IOException;

public interface FileService {

    boolean fileExist(final String srcFilePath) throws InvalidFileException;

    JsonNode convertToJson(final String srcFilePath) throws IOException;

    void storeAtDestination(final String destFilePath);

}
