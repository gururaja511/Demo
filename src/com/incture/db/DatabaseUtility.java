package com.incture.db;

import java.util.ArrayList;
import java.sql.*;  

public class DatabaseUtility {
	
	public static void main(String[] args) {
		DatabaseUtility db=new DatabaseUtility();
		db.getUserPort("admin");
	}  
	
	public static Connection gCon=null;
	
	public static Connection getDbConnection(){
		Connection con=null;
		try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();;  
		 con 	=DriverManager.getConnection( "jdbc:mysql://localhost/sys","root","ConTIntegratioN@321");
		//con 	=DriverManager.getConnection( "jdbc:mysql://115.110.70.127/sys","root","ConTIntegratioN@321");
		}catch(Exception e){
		System.out.println(e.getMessage());	
			
		}
		return con;
	}
	
	
	public String getUserPort(String userName){
		String portNo=null;
		try{
		if((DatabaseUtility.gCon==null))
			DatabaseUtility.gCon=DatabaseUtility.getDbConnection();  
		Statement stmt=DatabaseUtility.gCon.createStatement();  
		ResultSet rs=stmt.executeQuery("select C_PORT from CUSTOMER where CUST_NAME='"+userName+"'");  
		/*while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  */
		if(rs.next())
			portNo=rs.getString("C_PORT");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return portNo;
	}
	
public boolean verifyUserExists(String userName,String password){
boolean verifyUser=false;
	try{  
		//System.out.println(userName +" and "+ password+" from method");
		
		if((DatabaseUtility.gCon==null))
			DatabaseUtility.gCon=DatabaseUtility.getDbConnection();  
		Statement stmt=DatabaseUtility.gCon.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from CUSTOMER where CUST_NAME='"+userName+"' and C_PASSWORD='"+password+"'");  
		/*while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  */
		if(rs.next())
			verifyUser=true;
		//con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}  
	return verifyUser;	
		
}
	
	
public ArrayList<String> getUserProjectList(String user){
	//System.out.println(user);
	
	ArrayList<String> list =new ArrayList<>();
	list.add("");
	list.add("TestCase1:com.incture.proj.testNg.TestCase1");
	list.add("TestCase2:com.incture.proj.testNg.TestCase2");
	list.add("TestCase3:com.incture.proj.testNg.TestCase3");
	list.add("TestCase4:com.incture.proj.testNg.TestCase4");
	list.add("TestCase5:com.incture.proj.testNg.TestCase5");
	return list;
}

}
