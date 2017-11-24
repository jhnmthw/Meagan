//package com.tavant.nfr.meagan.jmeter;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.jxls.common.Context;
//import org.jxls.template.SimpleExporter;
//import org.jxls.util.JxlsHelper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class ExcelSheetManagerBackup {
//
//	private static String templatePath = "D:\\Jmeter_Reporting_Template.xlsx";
//
//	static Logger logger = LoggerFactory.getLogger(ExcelSheetManagerBackup.class);
//
//	public static void main(String args[]) throws ParseException, IOException {
//
//		JmeterResultsSummary summary = new JmeterResultsSummary();
//		summary.setDuration("1200");
//		summary.setRequests(1000);
//		summary.setRequestsPerSecond(2);
//		summary.setTime("2121");
//		summary.setUsers(10);
//
//		logger.info("Creating JMeter Report from Template....");
//
//		try (FileInputStream is = new FileInputStream(new File(templatePath))) {
//			try (FileOutputStream os = new FileOutputStream("target/Report.xlsx")) {
//				SimpleExporter exporter = new SimpleExporter();
//				exporter.registerGridTemplate(is);
//				List<String> headers = Arrays.asList("No of Users", "Time", "Duration in seconds",
//						"Number of requests", "Requests per Second");
//				List<JmeterResultsSummary> summarys = new ArrayList<JmeterResultsSummary>();
//				summarys.add(summary);
//				 Context context = new Context();
//				 context.putVar("headers",headers);
//				exporter.gridExport(headers, summarys, "users, time, duration, requests, requestsPerSecond", os);
//
//
//			}
//
//
//		}
//
//	}
//
//}