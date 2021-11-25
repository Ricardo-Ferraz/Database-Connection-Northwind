package br.com.unicap.bd2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import br.com.unicap.bd2.model.Order;
import br.com.unicap.bd2.util.ConnectionFactory;

public class OrderDAO {

	private static OrderDAO instance;
	
	private OrderDAO() {
	}

	public static OrderDAO getInstance() {
		if (instance == null) {
			instance = new OrderDAO();
		}
		return instance;
	}

	private List<Order> readConfig(String query) throws SQLException {
		List<Order> list = new ArrayList<Order>();

		Connection con = ConnectionFactory.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while (rs.next()) {

			/*
			 * String orderId, String customerId, String employeeId, String
			 * orderDate, String requiredDate, String shippedDate, String shipVia, String freight,
			 * String shipName, String shipAddress, String shipCity, String shipRegion, String shipPostalCode,
			 * String shipCountry
			 */
			Order aux = new Order(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
					rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14));
			list.add(aux);
		}
		ConnectionFactory.closeConnection(con, st, rs);
		return list;
	}
	
	private List<Order> readAll() throws SQLException {
		String query = "SELECT * FROM Orders;";
		return readConfig(query);
	}

	public String read() throws SQLException {
		StringBuilder sb = new StringBuilder();
		List<Order> list = readAll();

		for (Order c : list) {
			sb.append(c).append("\n");
		}

		return sb.toString();
	}

	public String readOne(String orderId) throws Exception, SQLException {
		String query = "select * from Orders where OrderID = "+orderId;
		List<Order> list = readConfig(query);

		for (Order c : list) {
			return c.toString();
		}
		throw new Exception("orderId n�o encontrado no banco!");
	}

	public String create(String[] inputs) throws SQLException {
		String sql = "INSERT INTO Orders(CustomerID,EmployeeID,OrderDate,RequiredDate,ShippedDate,ShipVia,Freight,ShipName,ShipAddress,ShipCity,ShipRegion,ShipPostalCode,ShipCountry) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement p = con.prepareStatement(sql);
		for(int i=1; i <= inputs.length; i++) {
			if((i-1 == 2 || i-1 == 3 || i-1 == 4) && inputs[i-1] != null) {
		        Timestamp date = Timestamp.valueOf(inputs[i-1]);
		        p.setTimestamp(i, date);
			}
			else {
				p.setString(i, inputs[i-1]);
			}
			
		}
		p.executeUpdate();
		ConnectionFactory.closeConnection(con, p);
		String aux= getNewOrderId();
		return aux;
	}
	
	private String getNewOrderId() throws SQLException{
		String aux="";
		String query= "SELECT * FROM Orders ORDER BY OrderID DESC ";
		Connection con = ConnectionFactory.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		if(rs.next()) {
			aux=rs.getString(1);
		}
		
		return aux;
	}
}
