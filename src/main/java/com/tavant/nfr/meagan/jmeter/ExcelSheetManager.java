package com.tavant.nfr.meagan.jmeter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tavant.nfr.meagan.jmeter.JmeterResultsSummary;

public class ExcelSheetManager {

	private static String templatePath = "target/jmeter/Jmeter_Reporting_Template.xlsx";
	private static String reportPath = "target/jmeter/results/Report.xlsx";
	static Logger logger = LoggerFactory.getLogger(ExcelSheetManager.class);

	public static void main(String args[]) throws ParseException, IOException {
		
		logger.info("Processing JMeter Report Template....");
		prepareTemplate();
		
		JmeterResultsSummary summary = new JmeterResultsSummary();
		
		logger.info("Creating JMeter Report from Template....");

		createReport(summary);

	}
	
	private static void createReport(JmeterResultsSummary summary) throws FileNotFoundException, IOException{
		try (FileInputStream is = new FileInputStream(new File(templatePath))) {
			try (FileOutputStream os = new FileOutputStream(reportPath)) {
				List<JmeterResultsSummary> summarys = new ArrayList<JmeterResultsSummary>();
				summarys.add(summary);
				Context context = new Context();
				context.putVar("summarys", summarys);
				JxlsHelper.getInstance().processTemplate(is, os, context);

			}

		}
	}
	
	private static void prepareTemplate() throws ParseException, IOException {
		Workbook workbook = getWorkbook(templatePath);
		Sheet sheet = workbook.getSheetAt(0);

		int rowCount = findRow(sheet, "Transactions");
		;

		String[] placeholderArray = { "summary.transactions", "summary.samples", "summary.averageResponseTime",
				"summary.minimumResponseTime", "summary.maximumResponseTime",
				"summary.stdDeviation", "summary.error", "summary.transactionsPerSec", "summary.kbPerSecond",
				"summary.averageBytes" };

		String testFileName = new File(System.getProperty("maven.multiModuleProjectDirectory") + "/src/test/jmeter/")
				.listFiles()[0].getName().split(".jmx")[0];

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMdd");

		int noOfTransactions = Files.readAllLines(Paths.get(
				System.getProperty("maven.multiModuleProjectDirectory") + "/target/jmeter/results/default-durations-"
						+ LocalDate.now().format(formatter) + "-" + testFileName + ".csv"))
				.size() - 1;

		for (int currentRow = 0; currentRow < noOfTransactions; currentRow++) {
			Row row = sheet.createRow(++rowCount);
			writePlaceholderArrayToRows(placeholderArray, row, currentRow);
		}
		
		String[] placeholders = {"summary.users", "summary.time","summary.duration","summary.requests","summary.requestsPerSecond"};
		rowCount = findRow(sheet, "Summary");

		for (int currentRow = 0; currentRow < placeholders.length; currentRow++) {
			Row row = sheet.getRow(++rowCount);
			writePlaceholderToRows(placeholders[currentRow], row);
		}
		try (FileOutputStream outputStream = new FileOutputStream(templatePath)) {
			workbook.write(outputStream);
		}

	}

	private static Workbook getWorkbook(String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(new FileInputStream(excelFilePath));
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(new FileInputStream(excelFilePath));
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	private static int findRow(Sheet sheet, String cellContent) {
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellTypeEnum() == CellType.STRING) {
					if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
						return row.getRowNum();
					}
				}
			}
		}
		return 0;
	}

	private static void writePlaceholderArrayToRows(String[] placeholders, Row row, int placehodlerIndex) {

		Cell cell;

		for (int i = 1; i <= placeholders.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue("${" + placeholders[i - 1] + "[" + placehodlerIndex + "]}");
		}

	}
	
	private static void writePlaceholderToRows(String placeholder, Row row) {

		Cell cell;


			cell = row.createCell(2);
			CellStyle cs = row.getCell(1).getCellStyle();
			cs.setAlignment(HorizontalAlignment.RIGHT);
			cell.setCellStyle(cs);
			cell.setCellValue("${" + placeholder + "}");
		

	}

}