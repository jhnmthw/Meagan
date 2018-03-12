package com.tavant.nfr.meagan.jmeter;

public class Records {
	private String transaction;
	private int noOfSamples; 
	private double averageResponseTime;
	private double  minimumResponseTime;
	private double maximumResponseTime;
	private double ninetyPercentLine;
	private double stdDeviation;
	private double error;
	private double transactionsPerSec;
	private double averageBytes;
	private double kbPerSecond;
	
	
	public Records(){
		
		transaction = "";
		noOfSamples = 0;
		averageResponseTime = 0.0;
		error = 0.0;
		minimumResponseTime = 0.0;
		maximumResponseTime = 0.0;
		stdDeviation = 0.0;
		averageBytes = 0.0;
		transactionsPerSec = 0.0;
		kbPerSecond = 0.0;
		ninetyPercentLine = 0.0;
		
	}
	
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public int getNoOfSamples() {
		return noOfSamples;
	}
	public void setNoOfSamples(int noOfSamples) {
		this.noOfSamples = noOfSamples;
	}
	public double getKbPerSecond() {
		return kbPerSecond;
	}
	public void setKbPerSecond(double kbPerSecond) {
		this.kbPerSecond = kbPerSecond;
	}
	public double getAverageResponseTime() {
		return averageResponseTime;
	}
	public void setAverageResponseTime(double averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}
	public double getMinimumResponseTime() {
		return minimumResponseTime;
	}
	public void setMinimumResponseTime(double minimumResponseTime) {
		this.minimumResponseTime = minimumResponseTime;
	}
	public double getMaximumResponseTime() {
		return maximumResponseTime;
	}
	public void setMaximumResponseTime(double maximumResponseTime) {
		this.maximumResponseTime = maximumResponseTime;
	}
	public double getNinetyPercentLine() {
		return ninetyPercentLine;
	}
	public void setNinetyPercentLine(double ninetyPercentLine) {
		this.ninetyPercentLine = ninetyPercentLine;
	}
	public double getAverageBytes() {
		return averageBytes;
	}
	public void setAverageBytes(double averageBytes) {
		this.averageBytes = averageBytes;
	}
	public double getStdDeviation() {
		return stdDeviation;
	}
	public void setStdDeviation(double stdDeviation) {
		this.stdDeviation = stdDeviation;
	}
	public double getError() {
		return error;
	}
	public void setError(double error) {
		this.error = error;
	}
	public double getTransactionsPerSec() {
		return transactionsPerSec;
	}
	public void setTransactionsPerSec(double transactionsPerSec) {
		this.transactionsPerSec = transactionsPerSec;
	}

}
