package integration.dao;

import integration.connector.Connector;
import mockit.Mock;
import org.hsqldb.jdbcDriver;

import java.sql.*;

public class HQSQLConnectorStub implements Connector {
    private static final String DATABASE_URI = "jdbc:hsqldb:file:database/test/ADISysDB";
    private static final String DATABASE_USER = "sa";
    private static final String DATABASE_PASSWORD = "";

    private Connection connection = null;

    static {
        new jdbcDriver();
    }

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


    private static final String[] DELETE_QUERIES = new String[]{
            "DELETE FROM Cura CASCADE",
            "DELETE FROM Sofferenza CASCADE",
            "DELETE FROM Patologia CASCADE",
            "DELETE FROM Operazione CASCADE",
            "DELETE FROM Intervento CASCADE",
            "DELETE FROM Infermiere CASCADE",
            "DELETE FROM Paziente CASCADE",
    };


    public void deleteAll() {
        Statement statement = createDefaultStatement();
        for (String e : DELETE_QUERIES) {
            try {
                statement.execute(e);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
