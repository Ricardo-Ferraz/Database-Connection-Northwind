package br.com.unicap.bd2.controller;

import java.sql.SQLException;

import br.com.unicap.bd2.dao.OrderDAO;
import br.com.unicap.bd2.dao.OrderDetailDAO;

public class ControllerOrder {
	
	private OrderDAO dao = OrderDAO.getInstance();
	private OrderDetailDAO details = OrderDetailDAO.getInstance();
	
	public String read() throws SQLException{
		return this.dao.read();
	}
	
	public String readOne(String customerId) throws Exception, SQLException{
		return this.dao.readOne(customerId)+this.details.readAllFromId(customerId);
	}
	
	public String create(String[] inputs) throws SQLException{
		return this.dao.create(inputs);
	}
	
	public int createDetails(String query) throws SQLException{
		return this.details.createDetails(query);
	}

}
