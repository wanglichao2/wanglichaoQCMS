package com.iss.dataget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iss.util.DBManager;

import gov.ccm.netbar.interfaceImp.controlInfo.CustomerOnlineInfo;

public class UploadgetData {
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
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
