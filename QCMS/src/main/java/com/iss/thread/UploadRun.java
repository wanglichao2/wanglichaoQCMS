package com.iss.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		while(true){
			 try{
			   //Thread.sleep(1000*60*10);
				 Thread.sleep(5000*60);
				 System.out.println("=============线程的开始================");
				 WebServiceUtilUpload wuupload=new WebServiceUtilUpload();//这是一个上传的工具类
				 String key=wuupload.login();
				 System.out.println("=====每5秒钟登陆一下这个接口=======>"+key);
			   //  String res= wuupload.sendCustomerOnlineInfo(getAll());
			     //System.out.println("========上报结果：=========="+res);
				 //获取到从数据库中的数据
				 Date date= new Date();
				 int hours = date.getHours();
				  String res=null;
				 if(hours>=16){
					res= wuupload.sendCustomerOnlineInfo(getAll());//返回上传的结果successful/failing
					System.out.println("===========线程调用上传的结果：=====》"+res);
/*					 if(res.equals("successful")){
							 List<CustomerOnlineInfo> clist2=getAll();
				    	 for(int i=0;i<clist2.size();i++){
				    		String NetBar_code= clist2.get(i).getNetbar_code();
				    		int result=delte(NetBar_code);
				    		if(result>0){
				    			System.out.println("删除成功");
				    			 List<CustomerOnlineInfo> clist3=getAll();
				    			 if(clist3.size()<=0){
				    				 //break;
				    			 }
				    		}else{
				    			System.out.println("删除失败！");
				    		}
				    	 }
						
					 }*/
				 }
				
				/* List<CustomerOnlineInfo> clist=getAll();
				     for (int i = 0; i < clist.size(); i++) {
						System.out.println("网吧编号："+clist.get(i).getNetbar_code());
					}*/
			
				
			 }catch(Exception e){
				e.printStackTrace();
			 }
		}
	}
	/**
	 * 这个方法是从数据库中查询数据的方法，返回一个list集合
	 * @return
	 */
	 public List<CustomerOnlineInfo> getAll(){
   	  List<CustomerOnlineInfo> t_up_cuonliinfolist=new ArrayList<CustomerOnlineInfo>();
   	  CustomerOnlineInfo t=null;
   	  try {
   			conn=DBManager.getConnection();
   			String sql="select * from t_up_customeronlineinfo";
   			pstmt=conn.prepareStatement(sql);
   			rs=pstmt.executeQuery();
   			while(rs.next()){
   				t =new CustomerOnlineInfo();
   				t.setCustomer_num(rs.getString("customer_num"));
   				t.setNetbar_code(rs.getString("netbar_code"));
   				t.setReport_time(rs.getString("report_time"));
   				t_up_cuonliinfolist.add(t);
   			}
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}finally{
   			DBManager.closeResource(rs, pstmt, conn);
   		}
   	  return t_up_cuonliinfolist;
     }
	 public int delte(String netbar_code){
		    int result=0;
		    try {
				conn=DBManager.getConnection();
				String sql="delete from t_up_customeronlineinfo where netbar_code=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,netbar_code);
				result=pstmt.executeUpdate();
				if(result>0){
					System.out.println("删除成功");
				}else{
					System.out.println("删除失败！");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		 return result;
	 }

}
