package com.github.spranshu1.csvtojson.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.spranshu1.common.util.json.JSONHandler;
import com.github.spranshu1.csvtojson.constants.AppConstants;
import com.github.spranshu1.csvtojson.constants.Messages;
import com.github.spranshu1.csvtojson.exceptions.InvalidFileException;
import com.github.spranshu1.csvtojson.main.CsvToJsonApplication;
import com.github.spranshu1.csvtojson.util.FileUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
public class ExcelFileService implements FileService {

    private static final Logger log = LoggerFactory.getLogger(ExcelFileService.class);

    /**
     * Check if path is valid and file exist at path with valid file name.
     * Supported file extensions - .csv , .xlsx , .xls
     *
     * @param srcFilePath the source file path
     * @return True is valid file exist, False otherwise
     * @throws InvalidFileException the exception
     */
    @Override
    public boolean fileExist(String srcFilePath) throws InvalidFileException {
        final File f = new File(srcFilePath);
        boolean isValid = false;
        try {
            String path = f.getCanonicalPath();
            String ext = FileUtil.getFileExtension(path);
            if(AppConstants.EXT_CSV.equals(ext) || AppConstants.EXT_XLS.equals(ext) || AppConstants.EXT_XLSX.equals(ext)){
                log.info("Found [{}] at location  [{}]",FileUtil.getFileName(srcFilePath),path);
                isValid = true;
            }
        } catch (IOException e) {
            log.error(Messages.ERR_FILE_READ);
        }

        return isValid;
    }

    @Override
    public JsonNode convertToJson(String srcFilePath) throws IOException {
        if (StringUtils.isEmpty(CsvToJsonApplication.sheetName) && CsvToJsonApplication.sheetIndex < 0) {
            throw new IOException(Messages.ERR_XLS_SHEET);
        }
        FileInputStream excelFile = new FileInputStream(new File(srcFilePath));
        Workbook workbook = new XSSFWorkbook(excelFile);

        Sheet dataSheet = StringUtils.isEmpty(CsvToJsonApplication.sheetName)
                ? workbook.getSheetAt(CsvToJsonApplication.sheetIndex) : workbook.getSheet(CsvToJsonApplication.sheetName);

        Row headerRow = dataSheet.getRow(0);

        Iterator<Row> iterator = dataSheet.iterator();
        iterator.next(); // to skip header row
        ObjectNode parent = JSONHandler.getObjectMapper().createObjectNode();
        while (iterator.hasNext()) {

            Row currentRow = iterator.next();

            Iterator<Cell> cellIterator = currentRow.iterator();

            while (cellIterator.hasNext()) {

                Cell currentCell = cellIterator.next();

                if (currentCell.getCellType() == CellType.STRING) {
                    String field = headerRow.getCell(currentCell.getColumnIndex()).getStringCellValue();
                    String value = currentCell.getStringCellValue();
                    log.info("field:{} , value:{}",field,value);
                    ObjectNode node = JSONHandler.getObjectMapper().createObjectNode();
                    node.put(field,value);
                    parent = JSONHandler.mergeJsons(parent,node);
                } else if (currentCell.getCellType() == CellType.NUMERIC) {
                    String field = headerRow.getCell(currentCell.getColumnIndex()).getStringCellValue();
                    double value = currentCell.getNumericCellValue();
                    log.info("field:{} , value:{}",field,value);
                    ObjectNode node = JSONHandler.getObjectMapper().createObjectNode();
                    node.put(field,value);
                    parent = JSONHandler.mergeJsons(parent,node);
                }

            }
            System.out.println();
        }

        return parent;
    }

    @Override
    public void storeAtDestination(String destFilePath) {

    }
}
