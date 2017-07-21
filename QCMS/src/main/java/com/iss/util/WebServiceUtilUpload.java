package com.iss.util;

import java.net.ConnectException;
import java.rmi.RemoteException;
import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.iss.entity.t_up_customeronlineinfo;
import com.iss.vo.InterfaceConfig;

import gov.ccm.netbar.interfaceImp.controlInfo.CustomerOnlineInfo;
import gov.ccm.netbar.interfaceImp.controlInfo.ExigencyInfo;
import gov.ccm.netbar.interfaceImp.controlInfo.OnlineTimeTipInfo;
import gov.ccm.netbar.interfaceImp.controlInfo.UploadControlInfoServiceProxy;
import gov.ccm.netbar.interfaceImp.loginInfo.LoginInfoProxy;

public class WebServiceUtilUpload {
	private static JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
    private static String Uploadconserproxurl=" http://192.168.70.40:8080/netbar/services/ControlInfo?wsdl";//UploadControlInfoService
      //这个接口的地址
	private static  UploadControlInfoServiceProxy upinprox=new UploadControlInfoServiceProxy(Uploadconserproxurl);
	private static String result1="";
    public static Client createWsClient(InterfaceConfig config) {
		ClientImpl client = (ClientImpl) clientFactory.createClient(config
				.getUrl());
		client.setSynchronousTimeout(20000);
		HTTPConduit conduit = (HTTPConduit) client.getConduit();
		HTTPClientPolicy policy = new HTTPClientPolicy();
		policy.setConnectionTimeout(10000); // 连接超时时间
		policy.setReceiveTimeout(20000);// 请求超时时间.
		policy.setAsyncExecuteTimeout(10000);
		policy.setAsyncExecuteTimeoutRejection(true);
		conduit.setClient(policy);
		return client;
	}
	public static String netBarSyncLogin(InterfaceConfig config)throws Exception {
		long start = System.currentTimeMillis();
		System.out.println("start====>" + start);
		Object[] result = null;
		try {
			Client client = createWsClient(config);
			Object[] params = new Object[] { config.getUsername(),
					config.getPassword() };
			result = client.invoke(config.getMethod(), params);
		} catch (ConnectException ce) {
			ce.printStackTrace();
			throw new Exception("WebService连接服务端超时");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			long end = System.currentTimeMillis();
			System.out.println("end====>" + end + "---->" + (end - start)/ 1000);
		}
		System.out.println(result[0]);
		return result == null ? null : (String) result[0];
	}
	
	//登陆
	  public static String login(){
		  String key="";
			try {
				String loginurl="http://192.168.70.40:8080/netbar/services/loginInfo?wsdl";
				String username="350000";
				String password="000000";
				LoginInfoProxy loginProxy=new LoginInfoProxy(loginurl);
				 key = loginProxy.login(username, password);
			} catch (RemoteException e) {
				
				e.printStackTrace();
			}
			return key;
	  }
	//5.1.4.2场所上网信息上传
	public static String sendCustomerOnlineInfo( List<CustomerOnlineInfo> list) throws Exception{
		String result ="";
		try{
			login();
			CustomerOnlineInfo[] cusonliInfo=list.toArray(new CustomerOnlineInfo[]{});
			 result=upinprox.sendCustomerOnlineInfo(login(),cusonliInfo);
			 if(result.equals("successful")){
				 System.out.println("============上传成功=========="+result);
			 }
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("调用场所上网信息上传失败"+e.getMessage());
		}
		return result;
	}
	//上网时长提示信息上传
	public static String sendOnlineTimeInfo(List<OnlineTimeTipInfo> list) throws Exception{
	       //  result1="";
		try {
			login();
			OnlineTimeTipInfo[] onlineTimeTipInfo=list.toArray(new OnlineTimeTipInfo[]{});
			 result1=upinprox.sendOnlineTimeInfo(login(), onlineTimeTipInfo);
			if(result1.equals("successful"))
				System.out.println("===========上传成功=======>"+result1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("调用上网时长提示信息上传失败："+e.getMessage());
		}
		return result1;
	}
	//紧急状态信息上传
	public static String sendExigencyInfo(List<ExigencyInfo> list) throws Exception{
		try {
			 login();
			 ExigencyInfo[] exigencyInfo=list.toArray(new ExigencyInfo[]{});
			 result1=upinprox.sendExigencyInfo(login(), exigencyInfo);
			 if(result1.equals("successful")){
				 System.out.println("==========上传成功======》"+result1);
			 }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("调用紧急状态信息上传"+e.getMessage());
		}
		return result1;
		
	}
	
}
