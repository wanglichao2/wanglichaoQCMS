package com.iss.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iss.dataget.UploadgetData;
import com.iss.entity.t_up_customeronlineinfo;
import com.iss.test.WebServiceTest2;
import com.iss.test.UploadTest;
import com.iss.util.DBManager;
import com.iss.util.WebServiceUtilUpload;

import gov.ccm.netbar.interfaceImp.controlInfo.CustomerOnlineInfo;

public class UploadRun implements Runnable{
	private  Connection conn=null;
    private  ResultSet rs=null;
    private  PreparedStatement pstmt=null;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("1111");
		List<CustomerOnlineInfo> clist2=null;
		boolean flag=true;
		UploadgetData upgetdata=null;
		while(flag){
			int result=0;
			 try{
			   //Thread.sleep(1000*60*10);
				 Thread.sleep(1000*60*20);
				 System.out.println("=============线程的开始================");
				 WebServiceUtilUpload wuupload=new WebServiceUtilUpload();//这是一个上传的工具类
				 String key=wuupload.login();
				 System.out.println("=====每10分钟登陆一下这个接口=======>"+key);
				 Date date= new Date();
				 int hours = date.getHours();
				 String res=null;
				 if(hours==23){
					  upgetdata=new UploadgetData();  
					 if(upgetdata.getAll().size()>0){
							res= wuupload.sendCustomerOnlineInfo( upgetdata.getAll());//返回上传的结果successful/failing
							System.out.println("===========线程调用上传的结果：=====》"+res);
							 if(res.equals("successful")){
							      clist2= upgetdata.getAll();
						    	 for(int i=0;i<clist2.size();i++){
						    		String NetBar_code= clist2.get(i).getNetbar_code();
						    		 result= upgetdata.delte(NetBar_code);
						    		if(result>0){
						    			System.out.println("删除成功");
						    			// List<CustomerOnlineInfo> clist3= upgetdata.getAll();
						    		}else if(result<0){
						    			System.out.println("删除失败！");
						    			
						    		}
						    	 }
							 }
					 }else if(result<0){
						 //String key2=wuupload.login(); 
						 //System.out.println("======数据库中没有数据继续登陆====》"+key2);
						 return; 
					 } 
				 }
			 }catch(Exception e){
				e.printStackTrace();
				
			 }
			
		}
		
	}
	/**
	 * 这个方法是从数据库中查询数据的方法，返回一个list集合
	 * @return
	 */
	 

}
