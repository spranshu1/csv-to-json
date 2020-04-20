package com.github.spranshu1.csvtojson.util;

import com.github.spranshu1.csvtojson.constants.AppConstants;
import com.github.spranshu1.csvtojson.constants.Messages;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    /**
     * Gets filename from filepath.
     * The filename is the farthest element from the root in file directory
     * @param filePath the path where file exist
     * @return String representation of file name
     * @throws FileNotFoundException the exception
     */
    public static String getFileName(String filePath) throws FileNotFoundException {
        Path path = Paths.get(filePath);

        Path fileName = path.getFileName();

        if(fileName == null){
            throw new FileNotFoundException(Messages.ERR_FILE_NOT_FOUND);
        }

        return fileName.toString();
    }


    /**
     * Gets the file extension from file name
     * @param filename the file name
     * @return extension of file eg: .csv or .xlsx
     */
    public static String getFileExtension(final String filename){

        return filename.substring(filename.lastIndexOf(AppConstants.DOT));

    }
}
