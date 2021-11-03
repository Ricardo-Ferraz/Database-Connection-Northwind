package br.com.unicap.bd2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConnectionFactory {

	private static final String USER = "testeLogin";
	private static final String PASSWORD = "teste";
	private static final String PORT = "1433";
	private static final String DATABASE = "Northwind";
	private static final String URL = "jdbc:sqlserver://localhost:" + PORT + ";user=" + USER + ";password=" + PASSWORD
			+ ";databaseName=" + DATABASE;

	public static Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(URL);
			return con;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static void closeConnection(Connection c) {
		if (c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

	public static void closeConnection(Connection c, Statement s) {
		closeConnection(c);

		if (s != null) {
			try {
				s.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

	public static void closeConnection(Connection c, PreparedStatement s) {
		closeConnection(c);

		if (s != null) {
			try {
				s.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

	public static void closeConnection(Connection c, Statement s, ResultSet r) {
		closeConnection(c, s);

		if (r != null) {
			try {
				r.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

	public static void closeConnection(Connection c, PreparedStatement s, ResultSet r) {
		closeConnection(c, s);

		if (r != null) {
			try {
				r.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}
}
