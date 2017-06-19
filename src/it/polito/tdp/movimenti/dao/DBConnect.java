package it.polito.tdp.movimenti.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mchange.v2.c3p0.*;

public class DBConnect {

	private final static String jdbcURL = "jdbc:mysql://localhost/demografia?user=root";
	
	private static ComboPooledDataSource cpds = null;

	public static Connection getConnection() {

		try {
			if (cpds != null)
				return cpds.getConnection();

			cpds = new ComboPooledDataSource();
			cpds.setDriverClass("com.mysql.jdbc.Driver"); // loads the jdbc
															// driver
			cpds.setJdbcUrl(jdbcURL);
			//cpds.setUser("dbuser");
			//cpds.setPassword("dbpassword");
			
			cpds.setMaxStatements( 180 ); 
			
			return cpds.getConnection()  ;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossibile connettersi al database", e);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Impossibile connettersi al database", e);
		}

	}

	public static Connection getConnectionDriverManager() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Driver JDBC non trovato", e);
		}
		Connection c;
		try {
			c = DriverManager.getConnection(jdbcURL);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossibile connettersi al database", e);
		}

		return c;

	}

}
