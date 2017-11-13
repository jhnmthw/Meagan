package com.tavant.nfr.meagan.jmeter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.Context;
import org.jxls.template.SimpleExporter;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.jxls.util.TransformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelSheetManager {

	private static String templatePath = "D:\\Jmeter_Reporting_Template.xlsx";

	static Logger logger = LoggerFactory.getLogger(ExcelSheetManager.class);

	public static void main(String args[]) throws ParseException, IOException {

		JmeterResultsSummary summary = new JmeterResultsSummary();
		summary.setDuration("1200");
		summary.setRequests(1000);
		summary.setRequestsPerSecond(2);
		summary.setTime("2121");
		summary.setUsers(10);

		logger.info("Creating JMeter Report from Template....");

		try (FileInputStream is = new FileInputStream(new File(templatePath))) {
			try (FileOutputStream os = new FileOutputStream("target/Report.xlsx")) {
				SimpleExporter exporter = new SimpleExporter();
				exporter.registerGridTemplate(is);
				List<String> headers = Arrays.asList("No of Users", "Time", "Duration in seconds",
						"Number of requests", "Requests per Second");
				List<JmeterResultsSummary> summarys = new ArrayList<JmeterResultsSummary>();
				summarys.add(summary);
				 Context context = new Context();
				 context.putVar("headers",headers);
				exporter.gridExport(headers, summarys, "users, time, duration, requests, requestsPerSecond", os);

				// Transformer transformer =
				// TransformerFactory.createTransformer(is, os);
				// // creating XlsCommentAreaBuilder instance
				// AreaBuilder areaBuilder = new
				// XlsCommentAreaBuilder(transformer);
				// // using area builder to construct a list of processing areas
				// List<Area> xlsAreaList = areaBuilder.build();
				// // getting the main area from the list
				// Area xlsArea = xlsAreaList.get(0);

				// Context context = new Context();
				//
				// context.putVar("headers",headers);
				// context.putVar("data", summarys);
				// JxlsHelper.getInstance().processGridTemplateAtCell(transformer,
				// context, "users, time, duration, requests,
				// requestsPerSecond", "Sheet2!A1");

				// System.out.println("Creating area");

				// JxlsHelper.getInstance().processTemplate(is, os, context);

			}

			// logger.info("Running Simple Export");
			// try {
			//
			// // getting input stream for our report template file from
			// classpath
			// InputStream is =
			// ExcelSheetManager.class.getResourceAsStream(template);
			// // creating POI Workbook
			// Workbook workbook = WorkbookFactory.create(is);
			// // creating JxlsPlus transformer for the workbook
			// PoiTransformer transformer =
			// PoiTransformer.createTransformer(workbook);
			// // creating XlsCommentAreaBuilder instance
			// AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
			// // using area builder to construct a list of processing areas
			// List<Area> xlsAreaList = areaBuilder.build();
			// // getting the main area from the list
			// Area xlsArea = xlsAreaList.get(0);
			//
			// Context context = new Context();
			// // context.putVar("totalCellUpdater", new TotalCellUpdater());
			//
			// } catch (EncryptedDocumentException | InvalidFormatException |
			// IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// // now let's show how to register custom template
			// try (InputStream is =
			// ExcelSheetManager.class.getResourceAsStream(template)) {
			// try (OutputStream os2 = new
			// FileOutputStream("target/simple_export_output2.xlsx")) {
			// exporter.registerGridTemplate(is);
			// headers = Arrays.asList("Name", "Payment", "Birth Date");
			// exporter.gridExport(headers, employees, "name,payment,
			// birthDate,", os2);
			// }
		}

	}

}
