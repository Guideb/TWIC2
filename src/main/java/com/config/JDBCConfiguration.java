package com.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dao.VilleDAOImpl;

@Configuration
public class JDBCConfiguration {

	private static Logger logger = Logger.getLogger(VilleDAOImpl.class.getName());

	@SuppressWarnings("deprecation")
	@Bean
	public static Connection getConnection() {
		String dbDriver = "com.mysql.jdbc.Driver";

		String BDD = "mavenseance1";
		String url = "jdbc:mysql://localhost:3309/" + BDD;
		String user = "root";
		String pass = getPassword();
		
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
	
	@SuppressWarnings("deprecation")
	private static String getPassword() {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream("application.properties");
		try {
			properties.load(fichierProperties);
			return properties.getProperty("jdbc.password");

		} catch (IOException e) {
			logger.log(Priority.ERROR, "Echec de la lecture du fichier de propriété : " + e.getMessage(), e);
		}		
		return null;
	}
	
}
