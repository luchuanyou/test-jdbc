
package cn.com;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Hello 
{

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException 
	{
		testQueryPage();
		//testQuery();//��ѯ
		/* testDelete();//ɾ��
		testQuery();//ɾ�����ѯ
		  testInsert();//����
		testQuery();//������ѯ
		testUpdate();//����
		testQuery();//���º��ѯ	     
*/	}

	private static void testQueryPage() throws SQLException {
//		String SQL = "SELECT top ? * FROM [dbo].[user] where id not in(SELECT top ? id FROM [dbo].[user])";
		String SQL = "SELECT id,name FROM [dbo].[user] where id = ?";
		int size = 2;
		int page = 2;
		int beginSize = size * (page -1);
		
		//String SQL = "SELECT top "+size+" * FROM [dbo].[user] where id not in(SELECT top "+beginSize+" id FROM [dbo].[user])";
		Connection conn = SqlHelper.getCoonection();
		System.out.println("---------------�������ݿ�ɹ�");
		PreparedStatement ps = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
		ps.setObject(1, 2);
//		ps.setObject(2, 2);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("id") + ", " + rs.getString("name"));
		}
	}
	private static void testQuery() throws SQLException
	{
		String SQL = "SELECT id,name FROM [dbo].[user] ";
		ResultSet rs=SqlHelper.executeQuery(SQL);
		 while (rs.next()) 
		 {
	         System.out.println(rs.getString("id") + ", " + rs.getString("name"));
	      }
	}
	private static void testInsert()
	{
		 String SQL = "  insert into C_PlanType(PlanTypeID,PlanTypeName,DisplayOrder)values('7','����','7') ";
		 if(SqlHelper.executeUpdate(SQL))
		 {
			 System.out.println("����ɹ� ");
		 }else
		 {
			 System.out.println("����ʧ�� ");
		 }
	}
	private static void testUpdate()
	{
		 String  SQL = "  update  C_PlanType set PlanTypeName='�����޸�'  where PlanTypeID='7'";
		 if(SqlHelper.executeUpdate(SQL))
		 {
			 System.out.println("���³ɹ� ");
		 }else
		 {
			 System.out.println("����ʧ�� ");
		 }
	}
	private static void testDelete()
	{
		String SQL = "  delete from  C_PlanType   where PlanTypeID='7'";
		 if(SqlHelper.executeUpdate(SQL))
		 {
			 System.out.println("ɾ���ɹ� ");
		 }else
		 {
			 System.out.println("ɾ��ʧ�� ");
		 }
	}

}