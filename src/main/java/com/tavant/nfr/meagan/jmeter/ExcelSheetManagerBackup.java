//package com.tavant.nfr.meagan.jmeter;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.List;
//
//import org.apache.commons.lang.ArrayUtils;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.DataFormat;
//import org.apache.poi.ss.usermodel.HorizontalAlignment;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.jxls.common.Context;
//import org.jxls.util.JxlsHelper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//public class ExcelSheetManager {
//
//	private static String templatePath = "target/jmeter/Jmeter_Reporting_Template.xlsx";
//	private static String reportPath = "target/jmeter/results/Report-" + new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".xlsx";
//	static Logger logger = LoggerFactory.getLogger(ExcelSheetManager.class);
//
//	public static void main(String args[]) throws Exception {
//		
//		logger.info("Processing JMeter Report Template....");
//		
//		Workbook workbook = getWorkbook(templatePath);
//		prepareTemplate(workbook, workbook.getSheetAt(0),"success");
//		prepareTemplate(workbook, workbook.getSheetAt(1),"all");
//		prepareTemplate(workbook, workbook.getSheetAt(3), "error");
//		
//		
//		JmeterResultsSummary successSummary = new JmeterResultsSummary();
//		JmeterResultsSummary allSummary = new JmeterResultsSummary();
//		JmeterResultsSummary errorSummary = new JmeterResultsSummary();
//		
//		
//		successSummary.getSummary(new File(System.getProperty("maven.multiModuleProjectDirectory")
//					+ "/target/jmeter/results/SynthesisReport-" + LocalDate.now().format(successSummary.formatter) + "-success.csv"));
//		
//		successSummary.getSummaryHeaders();
//		allSummary.getSummaryHeaders();
//		errorSummary.getSummaryHeaders();
//		
//		
//		allSummary.getSummary(new File(System.getProperty("maven.multiModuleProjectDirectory")
//				+ "/target/jmeter/results/SynthesisReport-" + LocalDate.now().format(allSummary.formatter) + "-all.csv"));
//		errorSummary.getSummary(new File(System.getProperty("maven.multiModuleProjectDirectory")
//				+ "/target/jmeter/results/SynthesisReport-" + LocalDate.now().format(errorSummary.formatter) + "-error.csv"));
//		
//		
//		Records[] r1 = setRecords(allSummary);
//		Records[] r2 = setRecords(successSummary);
//		Records[] r3 = setRecords(errorSummary);
//		
//
//		Arrays.sort(r1, new RecordsComaparator());
//		Arrays.sort(r2, new RecordsComaparator());
//		Arrays.sort(r3, new RecordsComaparator());
//		
//		
//		setSortedRecordsToObjects(r1, allSummary);
//		setSortedRecordsToObjects(r2, successSummary);
//		setSortedRecordsToObjects(r3, errorSummary);
//		
//
//		
//		List<JmeterResultsSummary> summarys1 = new ArrayList<JmeterResultsSummary>();
//		List<JmeterResultsSummary> summarys2 = new ArrayList<JmeterResultsSummary>();
//		List<JmeterResultsSummary> summarys3 = new ArrayList<JmeterResultsSummary>();
//		
//		summarys1.add(successSummary);
//			
//		summarys2.add(allSummary);
//		
//
//		summarys3.add(errorSummary);
//		
//		logger.info("Creating JMeter Report from Template....");
//		
//		createReport(summarys1,summarys2,summarys3);
//			
//
//	}
//	
//	
//	private static Records[] setRecords(JmeterResultsSummary jrs) {
//		
//		Records[] r = new Records[jrs.samplerLabels.size()];
//		
//		for(int i=0;i<r.length;i++) {
//			r[i] = new Records();
//		}
//		
//		String[] transactions = jrs.getTransactions();
//		String[] noOfSamples = jrs.getnoOfSamples();
//		String[] averageResponseTime = jrs.getAverageResponseTime();
//		String[] error = jrs.getError();
//		String[] minimumResponseTime = jrs.getMinimumResponseTime();
//		String[] maximumResponseTime = jrs.getMaximumResponseTime();
//		String[] stdDeviation = jrs.getStdDeviation();
//		String[] averageBytes = jrs.getAverageBytes();
//		String[] transactionsPerSec = jrs.getTransactionsPerSec();
//		String[] kbPerSecond = jrs.getKbPerSecond();
//		String[] ninetyPercentLine = jrs.getNinetyPercentLine();
//		
//		for(int i=0;i<r.length;i++) {
//			
//			r[i].setTransaction(transactions[i]);
//			r[i].setNoOfSamples(Integer.parseInt(noOfSamples[i]));
//			r[i].setAverageResponseTime(Double.parseDouble(averageResponseTime[i]));
//			r[i].setMinimumResponseTime(Double.parseDouble(minimumResponseTime[i]));
//			r[i].setMaximumResponseTime(Double.parseDouble(maximumResponseTime[i]));
//			r[i].setNinetyPercentLine(Double.parseDouble(ninetyPercentLine[i]));
//			r[i].setStdDeviation(Double.parseDouble(stdDeviation[i]));
//			r[i].setError(Double.parseDouble(error[i].split("%")[0]));
//			r[i].setTransactionsPerSec(Double.parseDouble(transactionsPerSec[i]));
//			r[i].setAverageBytes(Double.parseDouble(averageBytes[i]));
//			r[i].setKbPerSecond(Double.parseDouble(kbPerSecond[i]));
//			
//		}
//		
//		return r;
//	}
//	
//	private static void setSortedRecordsToObjects(Records[] r, JmeterResultsSummary jrs) {
//		
//		String[] transactions = new String[jrs.samplerLabels.size()];
//		String[] noOfSamples = new String[jrs.samplerLabels.size()];
//		String[] averageResponseTime = new String[jrs.samplerLabels.size()];
//		String[] error = new String[jrs.samplerLabels.size()];
//		String[] minimumResponseTime = new String[jrs.samplerLabels.size()];
//		String[] maximumResponseTime = new String[jrs.samplerLabels.size()];
//		String[] stdDeviation = new String[jrs.samplerLabels.size()];
//		String[] averageBytes = new String[jrs.samplerLabels.size()];
//		String[] transactionsPerSec = new String[jrs.samplerLabels.size()];
//		String[] kbPerSecond = new String[jrs.samplerLabels.size()];
//		String[] ninetyPercentLine = new String[jrs.samplerLabels.size()];
//
//		for(int i=0;i<r.length;i++) {
//			
//			transactions[i] = r[i].getTransaction();
//			noOfSamples[i] = String.valueOf(r[i].getNoOfSamples());
//			averageResponseTime[i] = String.valueOf(r[i].getAverageResponseTime());
//			error[i] = String.valueOf(r[i].getError());
//			minimumResponseTime[i] = String.valueOf(r[i].getMinimumResponseTime());
//			maximumResponseTime[i] = String.valueOf(r[i].getMaximumResponseTime());
//			stdDeviation[i] = String.valueOf(r[i].getStdDeviation());
//			averageBytes[i] = String.valueOf(r[i].getAverageBytes());
//			transactionsPerSec[i] = String.valueOf(r[i].getTransactionsPerSec());
//			kbPerSecond[i] = String.valueOf(r[i].getKbPerSecond());
//			ninetyPercentLine[i] = String.valueOf(r[i].getNinetyPercentLine());
//			
//			}
//		
//		jrs.setTransactions(transactions);
//		jrs.setNoOfSamples(noOfSamples);
//		jrs.setAverageResponseTime(averageResponseTime);
//		jrs.setMinimumResponseTime(minimumResponseTime);
//		jrs.setMaximumResponseTime(maximumResponseTime);
//		jrs.setNinetyPercentLine(ninetyPercentLine);
//		jrs.setStdDeviation(stdDeviation);
//		jrs.setError(error);
//		jrs.setTransactionsPerSec(transactionsPerSec);
//		jrs.setAverageBytes(averageBytes);
//		jrs.setKbPerSecond(kbPerSecond);
//		
//		
//	}
//	
//	private static void createReport(List<JmeterResultsSummary> summarys1,List<JmeterResultsSummary> summarys2,List<JmeterResultsSummary> summarys3) throws FileNotFoundException, IOException{
//		try (FileInputStream is = new FileInputStream(new File(templatePath))) {
//			try (FileOutputStream os = new FileOutputStream(reportPath)) {
//				Context context = new Context();
//				
//				context.putVar("summarys1", summarys1);
//				context.putVar("summarys2", summarys2);
//				context.putVar("summarys3", summarys3);
//				
//				JxlsHelper.getInstance().processTemplate(is, os, context);
//
//
//			}
//
//		}
//	}
//	
//	private static void prepareTemplate(Workbook workbook, Sheet sheet, String type) throws ParseException, IOException {
//		
//
//		int rowCount = findRow(sheet, "Label");
//
//		String[] placeholderArray = { type + "Summary.transactions", type + "Summary.noOfSamples", type + "Summary.averageResponseTime",
//				type + "Summary.minimumResponseTime", type + "Summary.maximumResponseTime", type + "Summary.ninetyPercentLine",
//				type + "Summary.stdDeviation", type + "Summary.error", type + "Summary.transactionsPerSec", type + "Summary.kbPerSecond",
//				type + "Summary.averageBytes" };
//		
//
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMdd");
//
//
//		
//		CellStyle cs = sheet.getRow(findRow(sheet, "Label")+1).getCell(1).getCellStyle();
//		
//		DataFormat df = workbook.createDataFormat(); 
//		
//		for (int currentRow = 0; currentRow < Files.readAllLines(Paths.get(System.getProperty("maven.multiModuleProjectDirectory")
//				+ "/target/jmeter/results/SynthesisReport-" + LocalDate.now().format(formatter) + "-" + type + ".csv")).size()-2; currentRow++) {
//			Row row = sheet.createRow(++rowCount);
//			writePlaceholderArrayToRows(placeholderArray, row, currentRow, cs, df);
//		}
//		
//		if(type.equals("success")) {
//			
//		rowCount = findRow(sheet, "Summary");
//		
//
//			String[] placeholders = { type + "Summary.users", type + "Summary.time", type + "Summary.duration", type + "Summary.requests", type + "Summary.requestsPerSecond"};
//		
//			
//			for (int currentRow = 0; currentRow < placeholders.length; currentRow++) {
//			Row row = sheet.getRow(++rowCount);
//			writePlaceholderToRows(placeholders[currentRow], row);
//		}
//		}
//		try (FileOutputStream outputStream = new FileOutputStream(templatePath)) {
//			workbook.write(outputStream);
//		}
//	}
//
//	private static Workbook getWorkbook(String excelFilePath) throws IOException {
//		Workbook workbook = null;
//
//		if (excelFilePath.endsWith("xlsx")) {
//			workbook = new XSSFWorkbook(new FileInputStream(excelFilePath));
//		} else if (excelFilePath.endsWith("xls")) {
//			workbook = new HSSFWorkbook(new FileInputStream(excelFilePath));
//		} else {
//			throw new IllegalArgumentException("The specified file is not Excel file");
//		}
//
//		return workbook;
//	}
//
//	private static int findRow(Sheet sheet, String cellContent) {
//		for (Row row : sheet) {
//			for (Cell cell : row) {
//				if (cell.getCellTypeEnum() == CellType.STRING) {
//					if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
//						return row.getRowNum();
//					}
//				}
//			}
//		}
//		return 0;
//	}
//
//	private static void writePlaceholderArrayToRows(String[] placeholders, Row row, int placehodlerIndex, CellStyle cs, DataFormat df) {
//
//		Cell cell;		
//		
//		
//		for (int i = 1; i <= placeholders.length; i++) {
//			cell = row.createCell(i);
//			
//			if(i!=1)
//				cs.setDataFormat(df.getFormat("#,##0.0000"));
//
//			cell.setCellValue("${" + placeholders[i - 1] + "[" + placehodlerIndex + "]}");
//			
//			cell.setCellStyle(cs);
//		}
//		
//	}
//	
//	private static void writePlaceholderToRows(String placeholder, Row row) {
//
//		Cell cell;
//
//
//			cell = row.createCell(2);
//			CellStyle cs = row.getCell(1).getCellStyle();
//			cs.setAlignment(HorizontalAlignment.RIGHT);
//			cell.setCellStyle(cs);
//			cell.setCellValue("${" + placeholder + "}");
//		
//
//	}
//
//}