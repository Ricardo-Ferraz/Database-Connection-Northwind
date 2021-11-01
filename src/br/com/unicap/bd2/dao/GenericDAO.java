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


}
