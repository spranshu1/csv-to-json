package com.github.spranshu1.csvtojson.main;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.spranshu1.csvtojson.constants.Messages;
import com.github.spranshu1.csvtojson.exceptions.InvalidArgumentException;
import com.github.spranshu1.csvtojson.services.FileService;
import com.github.spranshu1.csvtojson.services.FileServiceFactory;
import com.github.spranshu1.csvtojson.util.CommonUtil;
import com.github.spranshu1.csvtojson.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;

@SpringBootApplication
@ComponentScan(basePackages = {"com.github.spranshu1.csvtojson"})
public class CsvToJsonApplication implements CommandLineRunner {

	private static final Logger log  = LoggerFactory.getLogger(CsvToJsonApplication.class);

	public static String source;

	public static String destination;

	public static String sheetName;

	public static Integer sheetIndex;

	@Autowired
	private FileServiceFactory fileServiceFactory;

	public static void main(String[] args) {

		ConfigurableApplicationContext configurableAppContext = null;
		try {
			configurableAppContext = SpringApplication.run(CsvToJsonApplication.class, args);
		} catch (Throwable e) {
			log.error(Messages.ERR_FATAL_MAIN,e.getStackTrace());
		} finally {
			if(configurableAppContext != null){
				configurableAppContext.close();
			}
		}
	}

	@Override
	public void run(String... args) throws Exception {
		resolveArgs(args);

		log.info("Source:[{}] , Destination:[{}], SheetName:[{}], SheetIndex:[{}]",source,destination,sheetName,sheetIndex);

        String ext = FileUtil.getFileExtension(FileUtil.getFileName(source));

        FileService fileService = fileServiceFactory.getService(ext,sheetName,sheetIndex);

        if(fileService.fileExist(source)){

        	final JsonNode jsonData = fileService.convertToJson(source);
			System.out.println(jsonData.toString());
        }

	}

	private void resolveArgs(String... args){
		int totalArgs = args.length;
		if(totalArgs < 2){
			throw new InvalidArgumentException(Messages.ERR_INVALID_ARGS);
		}

		if(totalArgs > 2){
			source = StringUtils.isEmpty(args[0]) ? null : args[0].trim();
			destination = StringUtils.isEmpty(args[1]) ? null : args[1].trim();
			if(CommonUtil.isNumeric(args[2])) {
				sheetIndex = Integer.parseInt(args[2]);
				sheetName = null;
			}else{
				sheetName = StringUtils.isEmpty(args[2]) ? null : args[2].trim();
				sheetIndex = null;
			}
		} else {
			source = StringUtils.isEmpty(args[0]) ? null : args[0].trim();
			destination = StringUtils.isEmpty(args[1]) ? null : args[1].trim();
			sheetIndex = 0;
			sheetName = null;
		}
	}

	public static void exit(int status){
		log.info(Messages.SYS_EXIT);
		System.exit(status);
	}
}
