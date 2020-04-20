package com.github.spranshu1.csvtojson.services;

import com.github.spranshu1.csvtojson.constants.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class FileServiceFactory {

    @Autowired
    private ExcelFileService excelFileService;

    public FileService getService(String service, String sheet, Integer sheetIndex) {
        service = StringUtils.isEmpty(service) ? "" : service.toLowerCase();

        switch (service){
            case AppConstants.EXT_XLSX:
            case AppConstants.EXT_XLS:
                return excelFileService;
            case AppConstants.EXT_CSV:
                return null;
            default:
                return null;
        }

    }

    public FileService getService(String service){
        return getService(service,null,null);
    }
}
