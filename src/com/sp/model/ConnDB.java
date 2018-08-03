package com.sp.model;
import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;
public class ConnDB {
	private Connection ct=null;
	public Connection getConn(){
		try{
	
		Context initCtx= new InitialContext();
		Context envCtx= (Context) initCtx.lookup("java:comp/env");
		DataSource sqlDS=(DataSource)envCtx.lookup("jdbc/userDB");
		ct=sqlDS.getConnection();
		
		
  //  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
//	String dbURL="jdbc:sqlserver://localhost:1433;databaseName=userMgm;integratedSecurity=true";
  //  String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
	///		"databaseName=userMgm;integratedSecurity=true;";
//	String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
//						"databaseName=userMgm;user=sqldba;password=Good4now!;";
 //   ct= DriverManager.getConnection(connectionUrl);
	

		}catch(Exception e){
			e.printStackTrace();
		}
	    return ct;
	}
}


