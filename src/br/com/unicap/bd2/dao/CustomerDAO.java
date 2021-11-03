package br.com.unicap.bd2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unicap.bd2.model.Customer;
import br.com.unicap.bd2.util.ConnectionFactory;

public class CustomerDAO {

	private static CustomerDAO instance;

	private CustomerDAO() {

	}

	public static CustomerDAO getInstance() {
		if (instance == null) {
			instance = new CustomerDAO();
		}
		return instance;
	}
	
	private List<Customer> readConfig(String query) throws SQLException {
		List<Customer> list = new ArrayList<Customer>();

		Connection con = ConnectionFactory.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while (rs.next()) {

			/*
			 * String customerId, String companyName, String contactName, String
			 * contactTitle, String address, String city, String region, String postalCode,
			 * String country, String phone, String fax
			 */
			Customer aux = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
					rs.getString(10), rs.getString(11));
			list.add(aux);
		}
		ConnectionFactory.closeConnection(con, st, rs);
		return list;
	}

	private List<Customer> readAll() throws SQLException {
		String query = "SELECT * FROM Customers;";
		return readConfig(query);
	}

	public String read() throws SQLException {
		StringBuilder sb = new StringBuilder();
		List<Customer> list = readAll();

		for (Customer c : list) {
			sb.append(c).append("\n");
		}

		return sb.toString();
	}

	public String readOne(String customerId) throws Exception, SQLException {
		String query = "select * from Customers where CustomerID = '"+customerId+"';";
		List<Customer> list = readConfig(query);

		for (Customer c : list) {
			return c.toString();
		}
		throw new Exception("CustomerId não encontrado no banco!");
	}

	public int create(String[] inputs) throws SQLException {
		String sql = "INSERT INTO Customers(CustomerID,CompanyName,ContactName,ContactTitle,Address,City,Region,PostalCode,Country,Phone,Fax) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement p = con.prepareStatement(sql);
		for(int i=1; i <= inputs.length; i++) {
			p.setString(i, inputs[i-1]);
		}
		int rows= p.executeUpdate();
		ConnectionFactory.closeConnection(con, p);
		
		return rows;
	}
	
	public int delete(String customerId) throws SQLException{
		String sql = "DELETE FROM Customers WHERE customerId= '"+customerId+"';";
		
		Connection con = ConnectionFactory.getConnection();
		Statement stmt = con.createStatement();
		
		int rows= stmt.executeUpdate(sql);
		
		ConnectionFactory.closeConnection(con, stmt);
		return rows;
	
	}
	
	public int update(String[] inputs, String customerId) throws SQLException, Exception{
		String sql= "UPDATE Customers SET CustomerID=?, CompanyName=?, ContactName=?, ContactTitle=?, Address=?, City=?, Region=?, PostalCode=?, Country=?, Phone=?, Fax=? WHERE CustomerID= '"+customerId+"';";
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement p = con.prepareStatement(sql);
		Customer c = readObject(customerId);
		
		for(int i=1; i <= inputs.length; i++) {
			if(inputs[i-1] != null) {
				p.setString(i, inputs[i-1]);
			}
			else {
				p.setString(i, c.getIndex(i));
			}
		}
		int rows= p.executeUpdate();
		ConnectionFactory.closeConnection(con, p);
		return rows;
	}
	
	private Customer readObject(String customerId) throws Exception, SQLException {
		List<Customer> list = readAll();

		for (Customer c : list) {
			if (c.getCustomerId().equalsIgnoreCase(customerId)) {
				return c;
			}
		}
		
		return null;
	}

}
