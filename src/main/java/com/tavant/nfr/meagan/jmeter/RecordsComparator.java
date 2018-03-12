package com.tavant.nfr.meagan.jmeter;

public class Transactions implements Comparable<Transactions>{
	
	private String label;
	private double averageResponseTime;
	
	public Transactions(String label, double averageResponseTime){
		
		this.label = label;
		this.averageResponseTime = averageResponseTime;
		
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getAverageResponseTime() {
		return averageResponseTime;
	}
	public void setAverageResponseTime(double averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}
	
	@Override
	public int compareTo(Transactions t) {
		
		return (int)(this.averageResponseTime - t.getAverageResponseTime());
	}
	
	

}
