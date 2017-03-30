package cn.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class TestDemo {

	public static void main(String[] args) throws SQLException {
		testQuery();
//		testQueryPage();
//		insertBatch();
	}
	private static void testQuery() throws SQLException {
		String sql = "SELECT id,name FROM [dbo].[user] where id = ?";
		
		Connection conn = SqlHelper.getCoonection();
		System.out.println("---------------连接数据库成功");
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, 2);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("id") + ", " + rs.getString("name"));
		}
		conn.close();
	}
	/**
	 * 分页查询
	 * @throws SQLException
	 */
	private static void testQueryPage() throws SQLException {
		int size = 2;
		int page = 2;
		int beginSize = size * (page -1);
		
		String sql = "SELECT top "+size+" * FROM [dbo].[user] where id not in(SELECT top "+beginSize+" id FROM [dbo].[user])";
		
		Connection conn = SqlHelper.getCoonection();
		System.out.println("---------------连接数据库成功");
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("id") + ", " + rs.getString("name"));
		}
	}
	/**
	 * 批量插入
	 * @throws SQLException
	 */
	private static void insertBatch() throws SQLException {
		String SQL = "INSERT INTO [dbo].[user](id,name) values(?,?)";
		
		Connection conn = SqlHelper.getCoonection();
		System.out.println("---------------连接数据库成功");
		PreparedStatement ps = conn.prepareStatement(SQL);
		for (int n = 0; n < 2; n++) {  
			  ps.setInt(1, n);
			  ps.setString(2, "name"+n);
			  ps.addBatch();  
			}
		int[] result = ps.executeBatch();
		System.out.println("result:"+Arrays.toString(result));
	}
	
}
