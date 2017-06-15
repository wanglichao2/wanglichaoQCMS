package com.iss.test;

import java.util.List;

import com.iss.test.UploadTest;

import gov.ccm.netbar.interfaceImp.controlInfo.CustomerOnlineInfo;

public class test {
	public static void main(String[] args) {
		UploadTest uploadTest = new UploadTest();
		List<CustomerOnlineInfo> all = uploadTest.getAll();
		for (CustomerOnlineInfo customerOnlineInfo : all) {
			System.out.println(customerOnlineInfo.toString());
		}
	}
}
