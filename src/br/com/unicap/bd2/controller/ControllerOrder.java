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
	
	public int create(String[] inputs) throws SQLException{ //Tem que ser trabalhado pra incluir os Order Details e retirar o OrderID do preenchimento
		return this.dao.create(inputs);
	}

}
