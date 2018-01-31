//package com.tavant.nfr.meagan.jmeter;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.Properties;
//
//public class JmeterResultsSummaryBackup {
//
//	private int users;
//	private String time;
//	private String duration;
//	private String requests;
//	private String requestsPerSecond;
//	private String[] transactions;
//	private String[] averageResponseTime;
//	private String[] minimumResponseTime;
//	private String[] maximumResponseTime;
//	private String[] ninetyPercentLine;
//	private String[] stdDeviation;
//	private String[] error;
//	private double[] transactionsPerSec;
//	private String[] bandwidth;
//	private int[] averageBytes;
//	private String[] samples;
//	private double[] kbPerSecond;
//	FileInputStream is;
//	BufferedReader br;
//
//	public JmeterResultsSummaryBackup() {
//
//		try {
//
//			is = new FileInputStream(new File("performance.config.properties"));
//
//			Properties p = new Properties();
//			p.load(is);
//			this.users = Integer.parseInt(p.getProperty("Users"));
//
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMdd");
//			String timestamp = Files
//					.readAllLines(Paths.get("target/jmeter/results/" + LocalDate.now().format(formatter) + "-test.txt"))
//					.get(1);
//
//			Date startDate, endDate;
//			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
//
//			String[] parts = timestamp.split("\\s");
//
//			int noOfTransactions = Files.readAllLines(Paths
//					.get("target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-test.csv"))
//					.size();
//			transactions = new String[noOfTransactions];
//			samples = new String[noOfTransactions];
//			averageResponseTime = new String[noOfTransactions];
//			error = new String[noOfTransactions];
//			minimumResponseTime = new String[noOfTransactions];
//			maximumResponseTime = new String[noOfTransactions];
//			stdDeviation = new String[noOfTransactions];
//			averageBytes = new int[noOfTransactions];
//			transactionsPerSec = new double[noOfTransactions];
//			kbPerSecond = new double[noOfTransactions];
//
//			try {
//				startDate = df.parse(parts[3].split("\\+")[0]);
//				endDate = df.parse(parts[5].split("\\+")[0]);
//			} catch (ParseException e) {
//				throw new RuntimeException("Failed to parse date: ", e);
//			}
//
//			this.time = startDate.toString() + " - " + endDate.toString();
//			this.duration = Files
//					.readAllLines(Paths.get("target/jmeter/results/" + LocalDate.now().format(formatter) + "-test.txt"))
//					.get(2).split("\\s")[10];
//			this.requests = Files
//					.readAllLines(Paths.get("target/jmeter/results/" + LocalDate.now().format(formatter) + "-test.txt"))
//					.get(3).split("\\s")[15];
//			this.requestsPerSecond = Files
//					.readAllLines(Paths.get("target/jmeter/results/" + LocalDate.now().format(formatter) + "-test.txt"))
//					.get(4).split("\\s")[6];
//
//			// this.transactions =
//			// Files.readAllLines(Paths.get("target/jmeter/results/default-durations-"+LocalDate.now().format(formatter)+"-test.csv")).get(1).split("\\;")[0].replaceAll("^\"|\"$",
//			// "");
//			for (int i = 1; i <= (noOfTransactions - 1); i++) {
//				this.transactions[i - 1] = Files.readAllLines(Paths.get(
//						"target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-test.csv"))
//						.get(i).split("\\;")[0].replaceAll("^\"|\"$", "");
//				this.samples[i - 1] = Files.readAllLines(Paths.get(
//						"target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-test.csv"))
//						.get(i).split("\\;")[1];
//				this.averageResponseTime[i - 1] = Files.readAllLines(Paths.get(
//						"target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-test.csv"))
//						.get(i).split("\\;")[4];
//				this.error[i - 1] = Files.readAllLines(Paths.get(
//						"target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-test.csv"))
//						.get(i).split("\\;")[9];
//				this.minimumResponseTime[i - 1] = Files.readAllLines(Paths.get(
//						"target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-test.csv"))
//						.get(i).split("\\;")[3];
//				this.maximumResponseTime[i - 1] = Files.readAllLines(Paths.get(
//						"target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-test.csv"))
//						.get(i).split("\\;")[5];
//				this.stdDeviation[i - 1] = Files.readAllLines(Paths.get(
//						"target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-test.csv"))
//						.get(i).split("\\;")[6];
//				this.averageBytes[i - 1] = Integer.parseInt(Files.readAllLines(Paths
//						.get("target/jmeter/results/default-sizes-" + LocalDate.now().format(formatter) + "-test.csv"))
//						.get(i).split("\\;")[4]);
//				this.transactionsPerSec[i-1] = Integer.parseInt(Files.readAllLines(Paths
//						.get("target/jmeter/results/default-durations-" + LocalDate.now().format(formatter) + "-test.csv"))
//						.get(i).split("\\;")[7]);
//				this.kbPerSecond[i-1] = this.transactionsPerSec[i-1]*this.averageBytes[i-1];
//			}
//
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	public int getUsers() {
//
//		return users;
//	}
//
//	public void setUsers(int users) {
//
//		// this.users = users;
//	}
//
//	public String getTime() {
//		return time;
//	}
//
//	public void setTime(String time) {
//		this.time = time;
//	}
//
//	public String getDuration() {
//		return duration;
//	}
//
//	public void setDuration(String duration) {
//		this.duration = duration;
//	}
//
//	public String getRequestsPerSecond() {
//		return requestsPerSecond;
//	}
//
//	public void setRequestsPerSecond(String requestsPerSecond) {
//		this.requestsPerSecond = requestsPerSecond;
//	}
//
//	public String getRequests() {
//		return requests;
//	}
//
//	public void setRequests(String requests) {
//		this.requests = requests;
//	}
//
//	public String[] getSamples() {
//		return samples;
//	}
//
//	public void setSamples(String[] samples) {
//		this.samples = samples;
//	}
//
//	public String[] getTransactions() {
//		return transactions;
//	}
//
//	public void setTransactions(String[] transactions) {
//		this.transactions = transactions;
//	}
//
//	public String[] getAverageResponseTime() {
//		return averageResponseTime;
//	}
//
//	public void setAverageResponseTime(String[] averageResponseTime) {
//		this.averageResponseTime = averageResponseTime;
//	}
//
//	public String[] getMinimumResponseTime() {
//		return minimumResponseTime;
//	}
//
//	public void setMinimumResponseTime(String[] minimumResponseTime) {
//		this.minimumResponseTime = minimumResponseTime;
//	}
//
//	public String[] getNinetyPercentLine() {
//		return ninetyPercentLine;
//	}
//
//	public void setNinetyPercentLine(String[] ninetyPercentLine) {
//		this.ninetyPercentLine = ninetyPercentLine;
//	}
//
//	public int[] getAverageBytes() {
//		return averageBytes;
//	}
//
//	public void setAverageBytes(int[] averageBytes) {
//		this.averageBytes = averageBytes;
//	}
//
//	public String[] getError() {
//		return error;
//	}
//
//	public void setError(String[] error) {
//		this.error = error;
//	}
//
//	public String[] getStdDeviation() {
//		return stdDeviation;
//	}
//
//	public void setStdDeviation(String[] stdDeviation) {
//		this.stdDeviation = stdDeviation;
//	}
//
//	public String[] getMaximumResponseTime() {
//		return maximumResponseTime;
//	}
//
//	public void setMaximumResponseTime(String[] maximumResponseTime) {
//		this.maximumResponseTime = maximumResponseTime;
//	}
//
//	public double[] getTransactionsPerSec() {
//		return transactionsPerSec;
//	}
//
//	public void setTransactionsPerSec(double[] transactionsPerSec) {
//		this.transactionsPerSec = transactionsPerSec;
//	}
//
//	public String[] getBandwidth() {
//		return bandwidth;
//	}
//
//	public void setBandwidth(String[] bandwidth) {
//		this.bandwidth = bandwidth;
//	}
//	public double[] getKbPerSecond() {
//		return kbPerSecond;
//	}
//
//	public void setKbPerSecond(double[] kbPerSecond) {
//		this.kbPerSecond = kbPerSecond;
//	}
//
//
//}
