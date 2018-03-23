package com.tavant.nfr.meagan.jmeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class JmeterResultsSummary {

	private String[] transactions;
	private String[] averageResponseTime;
	private String[] minimumResponseTime;
	private String[] maximumResponseTime;
	private String[] ninetyPercentLine;
	private String[] stdDeviation;
	private String[] error;
	private String[] transactionsPerSec;

	private String[] averageBytes;
	private String[] noOfSamples;
	private String[] kbPerSecond;

	List<String> samplerLabels;
	List<String> noOfSamplesList;
	List<String> averageResponseTimeList;
	List<String> minimumResponseTimeList;
	List<String> maximumResponseTimeList;
	List<String> ninetyPercentLineList;
	List<String> stdDeviationList;
	List<String> errorList;
	List<String> transactionsPerSecList;
	List<String> averageBytesList;
	List<String> kbPerSecondList;

	private int users;
	private String time;
	private String duration;
	private String requests;
	private String requestsPerSecond;

	DateTimeFormatter formatter;

	FileInputStream is;
	BufferedReader br;

	public JmeterResultsSummary() {

		samplerLabels = new ArrayList<String>();
		noOfSamplesList = new ArrayList<String>();
		averageResponseTimeList = new ArrayList<String>();
		minimumResponseTimeList = new ArrayList<String>();
		maximumResponseTimeList = new ArrayList<String>();
		ninetyPercentLineList = new ArrayList<String>();
		stdDeviationList = new ArrayList<String>();
		errorList = new ArrayList<String>();
		transactionsPerSecList = new ArrayList<String>();
		averageBytesList = new ArrayList<String>();
		kbPerSecondList = new ArrayList<String>();

		formatter = DateTimeFormatter.ofPattern("YYYYMMdd");

	}


	public void getSummary(File csvFile) {

		CSVParser parser;
		try {
			parser = CSVParser.parse(csvFile, Charset.defaultCharset(), CSVFormat.RFC4180);

			for (CSVRecord csvRecord : parser) {
				samplerLabels.add(csvRecord.get(0));
				noOfSamplesList.add(csvRecord.get(1));
				averageResponseTimeList.add(csvRecord.get(2));
				minimumResponseTimeList.add(csvRecord.get(3));
				maximumResponseTimeList.add(csvRecord.get(4));
				ninetyPercentLineList.add(csvRecord.get(5));
				stdDeviationList.add(csvRecord.get(6));
				errorList.add(csvRecord.get(7));
				transactionsPerSecList.add(csvRecord.get(8));
				kbPerSecondList.add(csvRecord.get(9));
				averageBytesList.add(csvRecord.get(10));

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// =================================Removing First and last element from the
		// list================================================
		samplerLabels.remove(0);
		noOfSamplesList.remove(0);
		averageResponseTimeList.remove(0);
		minimumResponseTimeList.remove(0);
		maximumResponseTimeList.remove(0);
		ninetyPercentLineList.remove(0);
		stdDeviationList.remove(0);
		errorList.remove(0);
		transactionsPerSecList.remove(0);
		averageBytesList.remove(0);
		kbPerSecondList.remove(0);

		// =================================Removing Last element from the
		// list================================================

		transactions = new String[samplerLabels.size()];
		noOfSamples = new String[samplerLabels.size()];
		averageResponseTime = new String[samplerLabels.size()];
		error = new String[samplerLabels.size()];
		minimumResponseTime = new String[samplerLabels.size()];
		maximumResponseTime = new String[samplerLabels.size()];
		stdDeviation = new String[samplerLabels.size()];
		averageBytes = new String[samplerLabels.size()];
		transactionsPerSec = new String[samplerLabels.size()];
		kbPerSecond = new String[samplerLabels.size()];
		ninetyPercentLine = new String[samplerLabels.size()];

		this.transactions = samplerLabels.toArray(transactions);
		this.noOfSamples = noOfSamplesList.toArray(noOfSamples);
		this.averageResponseTime = averageResponseTimeList.toArray(averageResponseTime);
		this.error = errorList.toArray(error);
		this.minimumResponseTime = minimumResponseTimeList.toArray(minimumResponseTime);
		this.maximumResponseTime = maximumResponseTimeList.toArray(maximumResponseTime);
		this.stdDeviation = stdDeviationList.toArray(stdDeviation);
		this.transactionsPerSec = transactionsPerSecList.toArray(transactionsPerSec);
		this.ninetyPercentLine = ninetyPercentLineList.toArray(ninetyPercentLine);
		this.averageBytes = averageBytesList.toArray(averageBytes);
		this.kbPerSecond = kbPerSecondList.toArray(kbPerSecond);

		for (int i = 0; i < samplerLabels.size(); i++) { //Converting to Seconds
			this.averageResponseTime[i] = Double.toString(Double.parseDouble(this.averageResponseTime[i]) / 1000);
			this.minimumResponseTime[i] = Double.toString(Double.parseDouble(this.minimumResponseTime[i]) / 1000);
			this.maximumResponseTime[i] = Double.toString(Double.parseDouble(this.maximumResponseTime[i]) / 1000);
			this.stdDeviation[i] = Double.toString(Double.parseDouble(this.stdDeviation[i]) / 1000);
			this.ninetyPercentLine[i] = Double.toString(Double.parseDouble(this.ninetyPercentLine[i]) / 1000);
		}

	}

	public void getSummaryHeaders() {

		try {

			is = new FileInputStream(new File("performance.config.properties"));

			Properties p = new Properties();
			p.load(is);
			String testFileName = p.getProperty("jmxPath");

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder;

			Document xmlDocument;
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "//ThreadGroup/stringProp[@name='ThreadGroup.num_threads']/text()";
			NodeList matches;

			try {

				builder = builderFactory.newDocumentBuilder();

				xmlDocument = builder
						.parse(new InputSource(new StringReader(FileUtils
								.readFileToString(new File(System.getProperty("maven.multiModuleProjectDirectory")
										+ "/src/test/jmeter/" + testFileName + ".jmx"), "UTF-8")
								.replaceFirst("1.0", "1.1"))));  // Replacing XML version with 1.1 so that invalid characters do not cause parse exception

				matches = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

				int count = 0;

				for (int i = 0; i < matches.getLength(); i++) {
					if (matches.item(i).getParentNode().getParentNode().getAttributes().getNamedItem("enabled")
							.getNodeValue().equals("true"))
						count += Integer.parseInt(matches.item(i).getTextContent());
				}

				this.setUsers(count);



			} catch (XPathExpressionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SAXException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (ParserConfigurationException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

			String timestamp = Files.readAllLines(Paths.get(System.getProperty("maven.multiModuleProjectDirectory")
					+ "/target/jmeter/results/" + LocalDate.now().format(formatter) + "-" + testFileName + ".txt"))
					.get(1);

			Date startDate, endDate;
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

			String[] parts = timestamp.split("\\s");


			try {
				startDate = df.parse(parts[3].split("\\+")[0]);
				endDate = df.parse(parts[5].split("\\+")[0]);
			} catch (ParseException e) {
				throw new RuntimeException("Failed to parse date: ", e);
			}

			this.setTime(startDate.toString() + " - " + endDate.toString());
			this.setDuration(Files
					.readAllLines(Paths
							.get(System.getProperty("maven.multiModuleProjectDirectory") + "/target/jmeter/results/"
									+ LocalDate.now().format(formatter) + "-" + testFileName + ".txt"))
					.get(2).split("\\s")[10]);
			this.setRequests(Files
					.readAllLines(Paths
							.get(System.getProperty("maven.multiModuleProjectDirectory") + "/target/jmeter/results/"
									+ LocalDate.now().format(formatter) + "-" + testFileName + ".txt"))
					.get(3).split("\\s")[15]);
			this.setRequestsPerSecond(Files
					.readAllLines(Paths
							.get(System.getProperty("maven.multiModuleProjectDirectory") + "/target/jmeter/results/"
									+ LocalDate.now().format(formatter) + "-" + testFileName + ".txt"))
					.get(4).split("\\s")[6]);



		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getnoOfSamples() {
		return noOfSamples;
	}

	public void setNoOfSamples(String[] noOfSamples) {
		this.noOfSamples = noOfSamples;
	}

	public String[] getTransactions() {
		return transactions;
	}

	public void setTransactions(String[] transactions) {
		this.transactions = transactions;
	}

	public String[] getAverageResponseTime() {
		return averageResponseTime;
	}

	public void setAverageResponseTime(String[] averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}

	public String[] getMinimumResponseTime() {
		return minimumResponseTime;
	}

	public void setMinimumResponseTime(String[] minimumResponseTime) {
		this.minimumResponseTime = minimumResponseTime;
	}

	public String[] getNinetyPercentLine() {
		return ninetyPercentLine;
	}

	public void setNinetyPercentLine(String[] ninetyPercentLine) {
		this.ninetyPercentLine = ninetyPercentLine;
	}

	public String[] getAverageBytes() {
		return averageBytes;
	}

	public void setAverageBytes(String[] averageBytes) {
		this.averageBytes = averageBytes;
	}

	public String[] getError() {
		return error;
	}

	public void setError(String[] error) {
		this.error = error;
	}

	public String[] getStdDeviation() {
		return stdDeviation;
	}

	public void setStdDeviation(String[] stdDeviation) {
		this.stdDeviation = stdDeviation;
	}

	public String[] getMaximumResponseTime() {
		return maximumResponseTime;
	}

	public void setMaximumResponseTime(String[] maximumResponseTime) {
		this.maximumResponseTime = maximumResponseTime;
	}

	public String[] getTransactionsPerSec() {
		return transactionsPerSec;
	}

	public void setTransactionsPerSec(String[] transactionsPerSec) {
		this.transactionsPerSec = transactionsPerSec;
	}

	public String[] getKbPerSecond() {
		return kbPerSecond;
	}

	public void setKbPerSecond(String[] kbPerSecond) {
		this.kbPerSecond = kbPerSecond;
	}

	public int getUsers() {
		return users;
	}

	public void setUsers(int users) {
		this.users = users;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRequests() {
		return requests;
	}

	public void setRequests(String requests) {
		this.requests = requests;
	}

	public String getRequestsPerSecond() {
		return requestsPerSecond;
	}

	public void setRequestsPerSecond(String requestsPerSecond) {
		this.requestsPerSecond = requestsPerSecond;
	}
	
}
