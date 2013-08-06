package integration.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HQSQLConnector implements Connector {
	private static final String DATABASE_URI = "jdbc:hsqldb:file:database/ADISysDB";
	private static final String DATABASE_USER = "sa";
	private static final String DATABASE_PASSWORD = "";

	private Connection connection = null;

	@Override
	public ResultSet executeReadQuery(String query) {
		Statement statement = createDefaultStatement();
		ResultSet queryResult = null;

		try {
			queryResult = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return queryResult;
	}

	@Override
	public ResultSet executeUpdateQuery(String query) {
		Statement statement = createDefaultStatement();
		ResultSet queryUpdateResult = null;

		try {
			statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			queryUpdateResult = statement.getGeneratedKeys();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		 //restituisce le tuple inserite/modificate
		return queryUpdateResult;
	}

	private void connect() {
		try {
			connection = DriverManager.getConnection(
					DATABASE_URI, 
					DATABASE_USER, 
					DATABASE_PASSWORD);
	
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	private Statement createDefaultStatement() {
		Statement statement = null;

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return statement;
	}

	protected void finalize() throws Throwable {
		connection.close();
		connection = null;
	}

	HQSQLConnector() {
		connect();
	}
}
