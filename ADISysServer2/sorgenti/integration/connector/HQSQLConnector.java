package integration.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HQSQLConnector {
	private static final String DATABASE_URI = "jdbc:hsqldb:file:database/ADISysData";
	private static final String DATABASE_USER = "asl";
	private static final String DATABASE_PASSWORD = "";

	private static Connection connection = null;

	static {
		connect();
	}

	public static ResultSet executeReadQuery(String query) {
		Statement statement = createDefaultStatement();
		ResultSet queryResult = null;

		try {
			queryResult = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return queryResult;
	}

	public static int executeUpdateQuery(String query) {
		Statement statement = createDefaultStatement();
		int numberOfModifiedRow = 0;

		try {
			numberOfModifiedRow = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numberOfModifiedRow;
	}

	private static void connect() {
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

	private static Statement createDefaultStatement() {
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

	private HQSQLConnector() {

	}
}
