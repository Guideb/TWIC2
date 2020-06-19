package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dao.VilleDAOImpl;
import com.mysql.jdbc.Messages;

@Configuration
public class JDBCConfiguration {

	private static Logger logger = Logger.getLogger(VilleDAOImpl.class.getName());

	@SuppressWarnings("deprecation")
	@Bean
	public static Connection getConnection() {
		String dbDriver = "com.mysql.jdbc.Driver";

		String BDD = "mavenseance1";
		String url = "jdbc:mysql://localhost:3309/" + BDD;
		String user = Messages.getString("jdbc.user");
		String pass = Messages.getString("jdbc.password");
		
		Connection connection = null;
		// L'essaie de connexion à votre base de donées
		try {
			Class.forName(dbDriver);
			// création de la connexion
			// if(connection == null) {
			connection = DriverManager.getConnection(url, user, pass);
			// }
		} catch (ClassNotFoundException e) {
			logger.log(Priority.ERROR, "Error, classNotFound.");
		} catch (SQLException e1) {
			logger.log(Priority.ERROR, "Error, SqlException.");
		}
		return connection;
	}
}
