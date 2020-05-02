package com.github.spranshu1.csvtojson.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.github.spranshu1.csvtojson.exceptions.InvalidFileException;

import java.io.IOException;

/**
 * The interface File service.
 */
public interface FileService {

    /**
     * File exist boolean.
     *
     * @param srcFilePath the src file path
     * @return the boolean
     * @throws InvalidFileException the invalid file exception
     */
    boolean fileExist(final String srcFilePath) throws InvalidFileException;

    /**
     * Convert to json node.
     *
     * @param srcFilePath the src file path
     * @return the json node
     * @throws IOException the io exception
     */
    JsonNode convert(final String srcFilePath) throws IOException;

    /**
     * Store at destination.
     *
     * @param destFilePath the dest file path
     * @param data         the data
     * @throws IOException the io exception
     */
    void storeAtDestination(final String destFilePath, final String data) throws IOException;

}
