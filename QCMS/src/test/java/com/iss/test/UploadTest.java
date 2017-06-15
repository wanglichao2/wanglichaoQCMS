package com.iss.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iss.entity.t_up_customeronlineinfo;
import com.iss.util.DBManager;

import gov.ccm.netbar.interfaceImp.controlInfo.CustomerOnlineInfo;

public class UploadTest {
	  private  Connection conn=null;
      private  ResultSet rs=null;
      private  PreparedStatement pstmt=null;
      
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
    				System.out.println(rs.getString("netbar_code")+rs.getString("customer_num")+rs.getString("report_time"));
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
      
      
      public static void main(String[] args) {
		UploadTest t =new UploadTest();
		t.getAll();
	   
	}
}
