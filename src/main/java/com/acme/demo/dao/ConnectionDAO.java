package com.acme.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDAO {
	private final static Logger LOGGER = Logger.getLogger("com.acme.demo.DbConnection");

	private  String username = "sa";
	private  String password = "";
	private  String databaseUrl = "jdbc:h2:~/test";

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.databaseUrl = connectionUrl;
	}

	public  Connection createConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", username);
		connectionProps.put("password", password);
		DriverManager.registerDriver(new org.h2.Driver());
		return DriverManager.getConnection(databaseUrl, connectionProps);

	}

	public void saveLog(String tipoLog, String message)throws SQLException {
		ConnectionDAO connectionDAO=new ConnectionDAO();
		String sql = "INSERT INTO Log_Values(tipo_log,message) VALUES(?,?)";
			Connection connection=connectionDAO.createConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);			
			preparedStatement.setString(1, tipoLog);
			preparedStatement.setString(2, message);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();	
			LOGGER.log(Level.INFO, "se registro log exitosamente");
	}

}
