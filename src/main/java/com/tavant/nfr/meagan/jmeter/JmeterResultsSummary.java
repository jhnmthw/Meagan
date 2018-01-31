package com.tavant.nfr.meagan.jmeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JmeterResultsSummary {

	private int users;
	private String time;
	private String duration;
	private String requests;
	private String requestsPerSecond;
	private String[] transactions;
	private float[] averageResponseTime;
	private float[] minimumResponseTime;
	private float[] maximumResponseTime;
	private String[] ninetyPercentLine;
	private String[] stdDeviation;
	private String[] error;
	private double[] transactionsPerSec;
	private String[] bandwidth;
	private double[] averageBytes;
	private String[] samples;
	private double[] kbPerSecond;
	FileInputStream is;
	BufferedReader br;

	public JmeterResultsSummary() {

		try {

			is = new FileInputStream(new File("performance.config.properties"));

			Properties p = new Properties();
			p.load(is);
			String testFileName =  new File(System.getProperty("maven.multiModuleProjectDirectory")+"/src/test/jmeter/").listFiles()[0].getName().split(".jmx")[0];
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder;

			Document xmlDocument;
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "//ThreadGroup/@testname | //ThreadGroup/stringProp[@name='ThreadGroup.num_threads']/text()";
			NodeList matches;
			try {
				
				builder = builderFactory.newDocumentBuilder();
				xmlDocument = builder.parse(new File(System.getProperty("maven.multiModuleProjectDirectory")+"/src/test/jmeter/" + testFileName +  ".jmx"));
				matches = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

				   this.users = Integer.parseInt(matches.item(matches.getLength()-1).getTextContent());

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







			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMdd");
			String timestamp = Files
					.readAllLines(Paths.get(System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/" + LocalDate.now().format(formatter) + "-"+testFileName+".txt"))
					.get(1);

			Date startDate, endDate;
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

			String[] parts = timestamp.split("\\s");

			int noOfTransactions = Files.readAllLines(Paths
					.get(System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-"+testFileName+".csv"))
					.size()-1;
			transactions = new String[noOfTransactions];
			samples = new String[noOfTransactions];
			averageResponseTime = new float[noOfTransactions];
			error = new String[noOfTransactions];
			minimumResponseTime = new float[noOfTransactions];
			maximumResponseTime = new float[noOfTransactions];
			stdDeviation = new String[noOfTransactions];
			averageBytes = new double[noOfTransactions];
			transactionsPerSec = new double[noOfTransactions];
			kbPerSecond = new double[noOfTransactions];

			try {
				startDate = df.parse(parts[3].split("\\+")[0]);
				endDate = df.parse(parts[5].split("\\+")[0]);
			} catch (ParseException e) {
				throw new RuntimeException("Failed to parse date: ", e);
			}

			this.time = startDate.toString() + " - " + endDate.toString();
			this.duration = Files
					.readAllLines(Paths.get(System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/" + LocalDate.now().format(formatter) + "-"+testFileName+".txt"))
					.get(2).split("\\s")[10];
			this.requests = Files
					.readAllLines(Paths.get(System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/" + LocalDate.now().format(formatter) + "-"+testFileName+".txt"))
					.get(3).split("\\s")[15];
			this.requestsPerSecond = Files
					.readAllLines(Paths.get(System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/" + LocalDate.now().format(formatter) + "-"+testFileName+".txt"))
					.get(4).split("\\s")[6];


			for (int i = 1; i <= noOfTransactions; i++) {
				this.transactions[i - 1] = Files.readAllLines(Paths.get(
						System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-"+testFileName+".csv"))
						.get(i).split("\\;")[0].replaceAll("^\"|\"$", "");
				this.samples[i - 1] = Files.readAllLines(Paths.get(
						System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-"+testFileName+".csv"))
						.get(i).split("\\;")[1];
				this.averageResponseTime[i - 1] = Float.parseFloat(Files.readAllLines(Paths.get(
						System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-"+testFileName+".csv"))
						.get(i).split("\\;")[4])/1000;
				this.error[i - 1] = Files.readAllLines(Paths.get(
						System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-"+testFileName+".csv"))
						.get(i).split("\\;")[9];
				this.minimumResponseTime[i - 1] = Float.parseFloat(Files.readAllLines(Paths.get(
						System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-"+testFileName+".csv"))
						.get(i).split("\\;")[3])/1000;
				this.maximumResponseTime[i - 1] = Float.parseFloat(Files.readAllLines(Paths.get(
						System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-"+testFileName+".csv"))
						.get(i).split("\\;")[5])/1000;
				this.stdDeviation[i - 1] = Files.readAllLines(Paths.get(
						System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-"+testFileName+".csv"))
						.get(i).split("\\;")[6];
				this.averageBytes[i - 1] = Double.parseDouble(Files.readAllLines(Paths
						.get(System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/default-sizes-" + LocalDate.now().format(formatter) + "-"+testFileName+".csv"))
						.get(i).split("\\;")[4]);
				this.transactionsPerSec[i-1] = Double.parseDouble(Files.readAllLines(Paths
						.get(System.getProperty("maven.multiModuleProjectDirectory")+"/target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-"+testFileName+".csv"))
						.get(i).split("\\;")[7]);
				this.kbPerSecond[i-1] = this.transactionsPerSec[i-1]*this.averageBytes[i-1]/1024;
				
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getUsers() {

		return users;
	}

	public void setUsers(int users) {

		// this.users = users;
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

	public String getRequestsPerSecond() {
		return requestsPerSecond;
	}

	public void setRequestsPerSecond(String requestsPerSecond) {
		this.requestsPerSecond = requestsPerSecond;
	}

	public String getRequests() {
		return requests;
	}

	public void setRequests(String requests) {
		this.requests = requests;
	}

	public String[] getSamples() {
		return samples;
	}

	public void setSamples(String[] samples) {
		this.samples = samples;
	}

	public String[] getTransactions() {
		return transactions;
	}

	public void setTransactions(String[] transactions) {
		this.transactions = transactions;
	}

	public float[] getAverageResponseTime() {
		return averageResponseTime;
	}

	public void setAverageResponseTime(float[] averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}

	public float[] getMinimumResponseTime() {
		return minimumResponseTime;
	}

	public void setMinimumResponseTime(float[] minimumResponseTime) {
		this.minimumResponseTime = minimumResponseTime;
	}

	public String[] getNinetyPercentLine() {
		return ninetyPercentLine;
	}

	public void setNinetyPercentLine(String[] ninetyPercentLine) {
		this.ninetyPercentLine = ninetyPercentLine;
	}

	public double[] getAverageBytes() {
		return averageBytes;
	}

	public void setAverageBytes(double[] averageBytes) {
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

	public float[] getMaximumResponseTime() {
		return maximumResponseTime;
	}

	public void setMaximumResponseTime(float[] maximumResponseTime) {
		this.maximumResponseTime = maximumResponseTime;
	}

	public double[] getTransactionsPerSec() {
		return transactionsPerSec;
	}

	public void setTransactionsPerSec(double[] transactionsPerSec) {
		this.transactionsPerSec = transactionsPerSec;
	}

	public String[] getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(String[] bandwidth) {
		this.bandwidth = bandwidth;
	}
	public double[] getKbPerSecond() {
		return kbPerSecond;
	}

	public void setKbPerSecond(double[] kbPerSecond) {
		this.kbPerSecond = kbPerSecond;
	}


}
