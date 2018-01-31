//package com.tavant.nfr.meagan.jmeter;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//
//public class HtmlReportProcessor {
//
//	public static Document parseReport(String reportPath) throws IOException {
//		
//		
//		WebClient webClient = new WebClient();
//		HtmlPage myPage = webClient.getPage(reportPath);
//		Document doc = Jsoup.parse(myPage.asXml());
//		webClient.close();
//		return doc;
//
//	}
//	
//	public void fetchTableData(){
//
//	
//	
//	}
//	
//	public static void main(String args[]) {
//		
//		Document doc = null;
//		try {
//		//	doc = parseReport("file://" + System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/reports/" + new File(System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/reports").listFiles()[0].getName()+"/index.html");
//		doc = parseReport("http://10.209.0.145:9090/#/prequal?creditVendor=CBC_CRE_1&branchId=42&nmlsId=87207&APP_TYPE=SHORT_APP");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String resultData[][] = null;
////		Element table = doc.selectFirst("table#statisticsTable");
////		Element tb = table.selectFirst("tbody[aria-live]");
////		 System.out.print(tb.getElementsByTag("tr").get(0).text());
////		Elements tr = tb.select("tr");
////		for(int i=0;i<tr.size();i++)
////		System.out.println(tr.get(i));
//		
//		 for (Element table : doc.select("table#statisticsTable")) {
//			// System.out.print(table.getAllElements());
//			 for(Element tb : table.getElementsByAttribute("aria-live"))
//			 {	// System.out.print(tb.getAllElements());
//		        for (Element row : tb.select("tr")) {
//		        	resultData = new String[tb.childNodeSize()][row.childNodeSize()];
//		        //	System.out.print(row.getAllElements());
//		            Elements tds = row.select("td");
//		            for(Element data : tds)
//		            	 for(int i=0;i<tb.childNodeSize();i++)
//		            		 for(int j=0;j<row.childNodeSize();j++)
//		            	 {
//		            		resultData[i][j] = data.getAllElements().toString();
//		            	}
//		        }
//		    }
//	}
//		 for(int i=0;i<resultData.length;i++)
//			 for(int j=0;j<resultData.length;j++)
//     		System.out.println(resultData[i][j]);
//	}
//}
