package br.com.unicap.bd2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.unicap.bd2.util.ConnectionFactory;

public class GenericDAO {
	
	private static GenericDAO instance;
	
	private GenericDAO() {
	}
	
	public static GenericDAO getInstance() {
		if(instance == null) {
			instance = new GenericDAO();
		}
		return instance;
	}
	
	public String readQuery(String query) throws SQLException{
		StringBuilder sb = new StringBuilder();
		
		Connection con = ConnectionFactory.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData rsmd= rs.getMetaData();
		
		while(rs.next()) {
			for(int i=1; i<= rsmd.getColumnCount(); i++ ) {
				sb.append(rsmd.getColumnName(i)).append(": ").append(rs.getString(i)).append("\n");
			}
			sb.append("\n");
		}
		
		ConnectionFactory.closeConnection(con, st, rs);
		return sb.toString();
	}
	
	public String executeProcedure(String[] input) throws SQLException{
		StringBuilder sb = new StringBuilder();
		if(input[1].equals("")) {
			input[1]= "13";
		}
		String query= "EXECUTE CountryAndMonth '"+input[0]+"',"+input[1]+";";
		
		Connection con = ConnectionFactory.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData rsmd= rs.getMetaData();
		
		while(rs.next()) {
			for(int i=1; i<= rsmd.getColumnCount(); i++ ) {
				sb.append(rsmd.getColumnName(i)).append(": ").append(rs.getString(i)).append("\n");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public void setProcedure() throws SQLException{
		String sql = "SELECT NAME FROM sys.objects WHERE type = 'P';";
		String drop= "DROP PROCEDURE CountryAndMonth";
		boolean flag= false;
		
		Connection con = ConnectionFactory.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			if(rs.getString(1).equals("CountryAndMonth")) {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(drop);
				ConnectionFactory.closeConnection(null, stmt);
				this.createProcedure(con);
				flag= true;
				break;
			}
		}
		
		if(!flag) {
			this.createProcedure(con);
		}
		
		ConnectionFactory.closeConnection(con,st, rs);
	}
	
	private void createProcedure(Connection con) throws SQLException{
		String create= "CREATE PROCEDURE CountryAndMonth\r\n"
				+ "	@customerCountry NVARCHAR(15),\r\n"
				+ "	@month tinyint\r\n"
				+ "AS\r\n"
				+ "SELECT \r\n"
				+ "	Orders.OrderID, \r\n"
				+ "	Customers.CustomerID, \r\n"
				+ "	[Order Details].ProductID, \r\n"
				+ "	[Order Details].UnitPrice, \r\n"
				+ "	[Order Details].Quantity, \r\n"
				+ "	[Order Details].Discount\r\n"
				+ "FROM \r\n"
				+ "	Customers JOIN Orders ON Customers.CustomerID=Orders.CustomerID\r\n"
				+ "JOIN \r\n"
				+ "	[Order Details] ON Orders.OrderID= [Order Details].OrderID\r\n"
				+ "JOIN\r\n"
				+ "	Products ON [Order Details].ProductID =Products.ProductID\r\n"
				+ "WHERE \r\n"
				+ "	Customers.Country=@customerCountry AND MONTH(Orders.OrderDate) = @month\r\n"
				+ "ORDER BY\r\n"
				+ "	Customers.CustomerID";
		Statement stmt = con.createStatement();
		stmt.executeUpdate(create);
	}
}
