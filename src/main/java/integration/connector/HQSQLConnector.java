package integration.connector;

import utility.FolderManager;

import java.io.File;
import java.sql.*;

public class HQSQLConnector implements Connector {
    private static final String DATABASE_URI = "jdbc:hsqldb:file:database/ADISysDB";
    private static final String DATABASE_USER = "sa";
    private static final String DATABASE_PASSWORD = "";

    private Connection connection = null;

    HQSQLConnector() {
        connect();
    }

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

    private static final String DB_FOLDER_STRING = "database";
    private static final File DB_FILE = new File(DB_FOLDER_STRING + "/ADIsysDB.script");

    private void connect() {
        FolderManager.createFolderIfNotExists(DB_FOLDER_STRING);

        boolean fileExists = DB_FILE.exists();

        try {
            connection = DriverManager.getConnection(
                    DATABASE_URI,
                    DATABASE_USER,
                    DATABASE_PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        if (!fileExists) {
            recreateAll();
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

    private void recreateAll() {
        String[] physicalModel = DefinitionStatements.PHYSICAL_MODEL.split(";(\\n)?");
        try {
            for(String query : physicalModel) {
                connection.prepareStatement(query).execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String...args) throws Throwable {
        HQSQLConnector connector = new HQSQLConnector();

        connector.recreateAll();
    }
}
