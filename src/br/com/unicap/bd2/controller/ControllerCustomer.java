package br.com.unicap.bd2.controller;

import java.sql.SQLException;

import br.com.unicap.bd2.dao.CustomerDAO;

public class ControllerCustomer {
	
	private CustomerDAO dao = CustomerDAO.getInstance();
	
	public String read() throws SQLException{
		return this.dao.read();
	}
	
	public String readOne(String customerId) throws Exception, SQLException{
		return this.dao.readOne(customerId);
	}
	
	public int create(String[] inputs) throws SQLException{
		return this.dao.create(inputs);
	}

}
