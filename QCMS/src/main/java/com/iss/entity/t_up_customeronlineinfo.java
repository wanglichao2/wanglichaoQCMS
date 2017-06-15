package com.iss.entity;

public class t_up_customeronlineinfo {
   private String netbarCode;
   private String customer_num;
   private String report_time;
public String getNetbarCode() {
	return netbarCode;
}
public void setNetbarCode(String netbarCode) {
	this.netbarCode = netbarCode;
}
public String getCustomer_num() {
	return customer_num;
}
public void setCustomer_num(String customer_num) {
	this.customer_num = customer_num;
}
public String getReport_time() {
	return report_time;
}
public void setReport_time(String report_time) {
	this.report_time = report_time;
}
public t_up_customeronlineinfo(String netbarCode, String customer_num, String report_time) {
	super();
	this.netbarCode = netbarCode;
	this.customer_num = customer_num;
	this.report_time = report_time;
}
public t_up_customeronlineinfo() {
	super();
}
@Override
public String toString() {
	return "t_up_customeronlineinfo [netbarCode=" + netbarCode + ", customer_num=" + customer_num + ", report_time="
			+ report_time + "]";
}
   
   
   
}
