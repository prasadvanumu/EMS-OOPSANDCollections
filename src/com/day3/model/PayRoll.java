package com.day3.model;

import java.math.BigDecimal;


public class PayRoll {
	private int empId;
	private BigDecimal basicPay;
	private BigDecimal hra;
	private int tax;
	private int otherDeductions;
	
	
	public PayRoll(int empId,int sal) {
		double s=sal/(12.0);
		s=s-200;
		s=s-3000;
		
		this.empId=empId;
		this .basicPay=new BigDecimal(0.7*s);
		this.hra=new BigDecimal(0.3*s);
		this.tax=3000;
		this.otherDeductions=200;
	}


	public int getEmpId() {
		return empId;
	}



	public BigDecimal getBasicPay() {
		return basicPay;
	}


	

	public BigDecimal getHra() {
		return hra;
	}


	


	public int getTax() {
		return tax;
	}


	


	public int getOtherDeductions() {
		return otherDeductions;
	}
	
	
	public BigDecimal getGrossPay() {
		return this.basicPay.add(hra);
	}
	public BigDecimal getNettPay() {
		return this.basicPay.add(hra).add(new BigDecimal(3200));
	}


	@Override
	public String toString() {
		return "PayRoll [empId=" + empId + ", basicPay=" + basicPay + ", hra=" + hra + ", tax=" + tax
				+ ", otherDeductions=" + otherDeductions + "]";
	}


	
	
	
	

}
