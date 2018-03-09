package com.tavant.nfr.meagan.jmeter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.aspose.cells.CellArea;
import com.aspose.cells.DataSorter;
import com.aspose.cells.SortOrder;


public class ExcelSheetManager {

	private static String templatePath = "target/jmeter/Jmeter_Reporting_Template.xlsx";
	private static String reportPath = "target/jmeter/results/Report-" + new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".xlsx";
	static Logger logger = LoggerFactory.getLogger(ExcelSheetManager.class);

	public static void main(String args[]) throws Exception {
		
		logger.info("Processing JMeter Report Template....");
		
		Workbook workbook = getWorkbook(templatePath);
		prepareTemplate(workbook, workbook.getSheetAt(0),"success");
		prepareTemplate(workbook, workbook.getSheetAt(1),"all");
		prepareTemplate(workbook, workbook.getSheetAt(3), "error");
		
		
		JmeterResultsSummary successSummary = new JmeterResultsSummary();
		JmeterResultsSummary allSummary = new JmeterResultsSummary();
		JmeterResultsSummary errorSummary = new JmeterResultsSummary();
		
		
		successSummary.getSummary(new File(System.getProperty("maven.multiModuleProjectDirectory")
					+ "/target/jmeter/results/SynthesisReport-" + LocalDate.now().format(successSummary.formatter) + "-success.csv"));
		
		successSummary.getSummaryHeaders();
		allSummary.getSummaryHeaders();
		errorSummary.getSummaryHeaders();
		
		
		allSummary.getSummary(new File(System.getProperty("maven.multiModuleProjectDirectory")
				+ "/target/jmeter/results/SynthesisReport-" + LocalDate.now().format(allSummary.formatter) + ".csv"));
		errorSummary.getSummary(new File(System.getProperty("maven.multiModuleProjectDirectory")
				+ "/target/jmeter/results/SynthesisReport-" + LocalDate.now().format(errorSummary.formatter) + "-errors.csv"));
		
		List<JmeterResultsSummary> summarys1 = new ArrayList<JmeterResultsSummary>();
		List<JmeterResultsSummary> summarys2 = new ArrayList<JmeterResultsSummary>();
		List<JmeterResultsSummary> summarys3 = new ArrayList<JmeterResultsSummary>();
		
		summarys1.add(successSummary);
			
		summarys2.add(allSummary);
		

		summarys3.add(errorSummary);
		
		logger.info("Creating JMeter Report from Template....");
		
		createReport(summarys1,summarys2,summarys3);
		
//		Transactions t[] = new Transactions[successSummary.samplerLabels.size()];
//		
//		for(int i=0;i<successSummary.samplerLabels.size();i++)
//		t[i] = new Transactions(successSummary.samplerLabels.get(i), Double.parseDouble(successSummary.averageResponseTimeList.get(i)));
//		
//		Arrays.sort(t);
//		
//		tList.add(t);
//		
		
		
		

		
		com.aspose.cells.Workbook reportWorkbook = new com.aspose.cells.Workbook(reportPath);
		
		//Obtain the DataSorter object in the workbook
		DataSorter sorter = reportWorkbook.getDataSorter();
		 
		//Set the first order
		sorter.setOrder1(SortOrder.ASCENDING);
		 
		//Define the first key.
		sorter.setKey1(0);
		 
		//Set the second order
		sorter.setOrder2(SortOrder.ASCENDING);
		 
		//Define the second key
		sorter.setKey2(1);
		 
		//Create a cells area (range).
		CellArea ca1 = new CellArea();
		CellArea ca2 = new CellArea();
		CellArea ca3 = new CellArea();
		 
		//Specify the start row index.
		ca1.StartRow = 12;
		//Specify the start column index.
		ca1.StartColumn = 1;
		//Specify the last row index.
		ca1.EndRow = successSummary.samplerLabels.size()+1;
		//Specify the last column index.
		ca1.EndColumn = 11;
		 
		//Sort data in the specified data range (A2:C10)
		sorter.sort(reportWorkbook.getWorksheets().get(0).getCells(), ca1);
		
		//Specify the start row index.
		ca2.StartRow = 3;
		//Specify the start column index.
		ca2.StartColumn = 1;
		//Specify the last row index.
		ca2.EndRow = allSummary.samplerLabels.size();
		//Specify the last column index.
		ca2.EndColumn = 11;

		
		sorter.sort(reportWorkbook.getWorksheets().get(1).getCells(), ca2);
		
		//Specify the start row index.
		ca3.StartRow = 6;
		//Specify the start column index.
		ca3.StartColumn = 1;
		//Specify the last row index.
		ca3.EndRow = errorSummary.samplerLabels.size()+1;
		//Specify the last column index.
		ca3.EndColumn = 11;
		
		sorter.sort(reportWorkbook.getWorksheets().get(3).getCells(), ca3);

        //Save the excel file.
		try {
			reportWorkbook.save(reportPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void createReport(List<JmeterResultsSummary> summarys1,List<JmeterResultsSummary> summarys2,List<JmeterResultsSummary> summarys3) throws FileNotFoundException, IOException{
		try (FileInputStream is = new FileInputStream(new File(templatePath))) {
			try (FileOutputStream os = new FileOutputStream(reportPath)) {
				Context context = new Context();
				
				context.putVar("summarys1", summarys1);
				context.putVar("summarys2", summarys2);
				context.putVar("summarys3", summarys3);
		//		context.putVar("tList", tList);
				
				JxlsHelper.getInstance().processTemplate(is, os, context);


			}

		}
	}
	
	private static void prepareTemplate(Workbook workbook, Sheet sheet, String type) throws ParseException, IOException {
		

		int rowCount = findRow(sheet, "Label");

		String[] placeholderArray = { type + "Summary.transactions", type + "Summary.noOfSamples", type + "Summary.averageResponseTime",
				type + "Summary.minimumResponseTime", type + "Summary.maximumResponseTime", type + "Summary.ninetyPercentLine",
				type + "Summary.stdDeviation", type + "Summary.error", type + "Summary.transactionsPerSec", type + "Summary.kbPerSecond",
				type + "Summary.averageBytes" };

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMdd");

		int noOfTransactions = Files.readAllLines(Paths.get(
				System.getProperty("maven.multiModuleProjectDirectory") + "/target/jmeter/results/AggregateReport-"
						+ LocalDate.now().format(formatter) + ".csv"))
				.size() - 2;

		for (int currentRow = 0; currentRow < noOfTransactions; currentRow++) {
			Row row = sheet.createRow(++rowCount);
			writePlaceholderArrayToRows(placeholderArray, row, currentRow);
		}
		
		if(type.equals("success")) {
			
		rowCount = findRow(sheet, "Summary");
		

			String[] placeholders = { type + "Summary.users", type + "Summary.time", type + "Summary.duration", type + "Summary.requests", type + "Summary.requestsPerSecond"};
		for (int currentRow = 0; currentRow < placeholders.length; currentRow++) {
			Row row = sheet.getRow(++rowCount);
			writePlaceholderToRows(placeholders[currentRow], row);
		}
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
			//BorderStyle bs = BorderStyle.MEDIUM;
			//cs.setBorderBottom(bs);
			cell.setCellStyle(cs);
			cell.setCellValue("${" + placeholder + "}");
		

	}

}