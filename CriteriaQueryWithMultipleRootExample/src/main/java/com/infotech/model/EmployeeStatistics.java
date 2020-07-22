package com.infotech.model;

public class EmployeeStatistics {

	private long empCount;
	private double maxSalary;
	private double minSalary;
	private double avgSalary;
	private double sumSalary;

	public EmployeeStatistics(long empCount, double maxSalary, double minSalary, double avgSalary, double sumSalary) {
		super();
		this.empCount = empCount;
		this.maxSalary = maxSalary;
		this.minSalary = minSalary;
		this.avgSalary = avgSalary;
		this.sumSalary = sumSalary;
	}

	public long getEmpCount() {
		return empCount;
	}

	public void setEmpCount(long empCount) {
		this.empCount = empCount;
	}

	public double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}

	public double getAvgSalary() {
		return avgSalary;
	}

	public void setAvgSalary(double avgSalary) {
		this.avgSalary = avgSalary;
	}

	public double getSumSalary() {
		return sumSalary;
	}

	public void setSumSalary(double sumSalary) {
		this.sumSalary = sumSalary;
	}

}
