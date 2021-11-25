package br.com.unicap.bd2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unicap.bd2.model.OrderDetail;
import br.com.unicap.bd2.util.ConnectionFactory;

public class OrderDetailDAO {

	private static OrderDetailDAO instance;

	private OrderDetailDAO()  {
		try {
			this.setTrigger();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static OrderDetailDAO getInstance() {
		if (instance == null) {
			instance = new OrderDetailDAO();
		}
		return instance;
	}
	
	private List<OrderDetail> readConfig(String query) throws SQLException{
		List<OrderDetail> list = new ArrayList<OrderDetail>();

		Connection con = ConnectionFactory.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while (rs.next()) {

			/*
			 * String orderId, String productId, String unitPrice, String quantity, String discount
			 */
			OrderDetail aux = new OrderDetail(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5));
			list.add(aux);
		}
		ConnectionFactory.closeConnection(con, st, rs);
		return list;
	}

	private List<OrderDetail> readAll() throws SQLException {
		String query = "SELECT * FROM [Order Details];";
		return readConfig(query);
	}

	public String read() throws SQLException {
		StringBuilder sb = new StringBuilder();
		List<OrderDetail> list = readAll();

		for (OrderDetail c : list) {
			sb.append(c).append("\n");
		}

		return sb.toString();
	}

	public String readOne(String orderId, String productId) throws Exception, SQLException {
		String query = "select * from [Order Details] where OrderID = "+orderId+" and ProductID = "+productId;
		List<OrderDetail> list = readConfig(query);
		for (OrderDetail c : list) {
			return c.toString();
		}
		throw new Exception("Order Detail n�o encontrado no banco!");
	}
	
	public List<OrderDetail> readAllFromId(String orderId) throws Exception, SQLException {
		String query = "select * from [Order Details] where OrderID = "+orderId;
		List<OrderDetail> list = readConfig(query);
		if(list.size() > 0) {
			return list;
		}
		throw new Exception("orderId n�o encontrado no banco!");
	}

	public int create(String[] inputs) throws SQLException {
		String sql = "INSERT INTO [Order Details](OrderID,ProductID,UnitPrice,Quantity,Discount) VALUES (?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement p = con.prepareStatement(sql);
		for(int i=1; i <= inputs.length; i++) {
			p.setString(i, inputs[i-1]);
		}
		int rows= p.executeUpdate();
		ConnectionFactory.closeConnection(con, p);
		
		return rows;
	}
	
	public int createDetails(String query) throws SQLException{
		Connection con = ConnectionFactory.getConnection();
		Statement st = con.createStatement();
		
		int rows= st.executeUpdate(query);
		ConnectionFactory.closeConnection(con, st);
		return rows;
	}
	
	private void setTrigger() throws SQLException {
		String sql = "CREATE TRIGGER ForbiddenBrazil ON [Order Details] \n"
				+ "FOR INSERT\n"
				+ "AS \n"
				+ "IF (\n"
				+ "        SELECT TOP(1) SUM(inserted.Quantity) FROM inserted JOIN Orders ON inserted.OrderID = Orders.OrderID\n"
				+ "    ) < 10\n"
				+ "    AND \n"
				+ "    (\n"
				+ "        SELECT TOP(1) Orders.ShipCountry FROM Orders JOIN inserted ON inserted.OrderID = Orders.OrderID\n"
				+ "    ) = 'Brazil'\n"
				+ "BEGIN\n"
				+ "PRINT 'É preciso comprar no mínimo 10 produtos no total caso seu navio saia do Brasil'\n"
				+ "ROLLBACK TRANSACTION\n"
				+ "END";
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		ConnectionFactory.closeConnection(con, statement);
		statement.executeUpdate();
	}
}
