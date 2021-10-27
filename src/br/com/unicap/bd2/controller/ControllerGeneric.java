package br.com.unicap.bd2.controller;

import java.sql.SQLException;

import br.com.unicap.bd2.dao.GenericDAO;

public class ControllerGeneric {
	
	private GenericDAO dao = GenericDAO.getInstance();
	
	public String query(String query) throws SQLException{
		return this.dao.readQuery(query);
	}

}
