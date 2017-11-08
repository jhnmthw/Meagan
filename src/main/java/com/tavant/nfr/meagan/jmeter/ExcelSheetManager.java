//package com.tavant.nfr.meagan.jmeter;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//import org.apache.poi.EncryptedDocumentException;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.jxls.area.Area;
//import org.jxls.builder.AreaBuilder;
//import org.jxls.builder.xls.XlsCommentAreaBuilder;
//import org.jxls.transform.poi.PoiTransformer;
//
//public class ExcelSheetManager {
//
//	public static void main()
//	{
//		
//		try {
//			
//			// getting input stream for our report template file from classpath
//			InputStream is = ExcelSheetManager.class.getResourceAsStream("object_collection_template.xls");
//			// creating POI Workbook
//			Workbook workbook = WorkbookFactory.create(is);
//			// creating JxlsPlus transformer for the workbook
//			PoiTransformer transformer = PoiTransformer.createTransformer(workbook);
//			// creating XlsCommentAreaBuilder instance
//			AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
//			// using area builder to construct a list of processing areas
//			List<Area> xlsAreaList = areaBuilder.build();
//			// getting the main area from the list
//			Area xlsArea = xlsAreaList.get(0);
//			
//
//		
//		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		
//	}
//	
//
//	
//}
