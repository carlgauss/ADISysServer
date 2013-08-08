package integration.dao.test;

import integration.connector.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mockit.Mock;

public class HQSQLConnectorStub implements Connector {
	private static final String DATABASE_URI = "jdbc:hsqldb:file:database/test/ADISysDB";
	private static final String DATABASE_USER = "sa";
	private static final String DATABASE_PASSWORD = "";

	private Connection connection = null;

	@Mock
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

	@Mock
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
	
	void close() throws Throwable {
		finalize();
	}

	public HQSQLConnectorStub() {
		connect();
	}
	
	
	private static final String[] DELETE_QUERIES = new String[] {
		"DELETE FROM Cellulare CASCADE",
		"DELETE FROM Operazione CASCADE",
		"DELETE FROM Intervento CASCADE",
		"DELETE FROM Infermiere CASCADE",
		"DELETE FROM Paziente CASCADE",
	};
	
	
	public void deleteAll() {
		Statement statement = createDefaultStatement();
		for(String e : DELETE_QUERIES) {
			try {
				statement.execute(e);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
