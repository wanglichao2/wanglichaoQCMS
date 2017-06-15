package com.iss.test;

import java.rmi.RemoteException;

import gov.ccm.netbar.interfaceImp.controlInfo.CustomerOnlineInfo;
import gov.ccm.netbar.interfaceImp.controlInfo.UploadControlInfoServiceProxy;
import gov.ccm.netbar.interfaceImp.loginInfo.LoginInfoProxy;

public class WebServiceTest2 {
  public static void main(String[] args) {
	  String loginurl="http://192.168.70.40:8080/netbar/services/loginInfo?wsdl";
		String username="350000";
		String password="000000";
		String Uploadconserproxurl=" http://192.168.70.40:8080/netbar/services/ControlInfo?wsdl";
		LoginInfoProxy loginProxy=new LoginInfoProxy(loginurl);
		try {
			String key=loginProxy.login(username, password);
			UploadControlInfoServiceProxy upinprox=new UploadControlInfoServiceProxy(Uploadconserproxurl);
			CustomerOnlineInfo c=new CustomerOnlineInfo();
			       c.setCustomer_num("2");
			       c.setNetbar_code("2222");
			       c.setReport_time("2015-10-10");
			       CustomerOnlineInfo[] cu=new CustomerOnlineInfo[]{c};  
			String res=upinprox.sendCustomerOnlineInfo(key,cu );
			System.out.println("=====jieguo======"+res);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
  
  
  
  
  
  
  
  
  
  }
}
