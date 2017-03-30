
package cn.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlHelper
{

	
	private static String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String dbURL="jdbc:sqlserver://172.18.33.68:1433;DatabaseName=inskuai.integration";

	private static String userName="xaolwei";

	private static String userPwd="123456";
	  
	  
	public static Connection  getCoonection()
	  {
		  try
		  {
		   Class.forName(driverName);
		   Connection conn=DriverManager.getConnection(dbURL,userName,userPwd);
		   return conn;
		  }

		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.print("----------------����ʧ��");
		  } 
		  return null;
	  }
	  
	public static ResultSet  executeQuery(String SQL)
	{  
		  try

		  {

		   Connection conn=getCoonection();
		   System.out.println("---------------�������ݿ�ɹ�");  
		  // String SQL = "SELECT PlanTypeID, PlanTypeName FROM C_PlanType ";
		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery(SQL);
		     /* while (rs.next()) 
		      {
		         System.out.println(rs.getString("PlanTypeID") + ", " + rs.getString("PlanTypeName"));
		      }*/
		     // rs.close();
		     // stmt.close(); 
		      return  rs;
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.print("----------------��ѯʧ��");
		  }
		  return null;
	}
	public static boolean  executeUpdate(String SQL)
	{  
		  try
		  {
		   Connection conn=getCoonection();
		   System.out.println("---------------�������ݿ�ɹ�");  
		  
		   Statement stmt = conn.createStatement();
		   int result = stmt.executeUpdate(SQL);
		   if(result>0)
			   return true;
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		   System.out.print("----------------����ʧ��");
		  }
		  return false;
	}
}