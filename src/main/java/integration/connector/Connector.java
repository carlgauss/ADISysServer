package integration.connector;

import java.sql.ResultSet;

public interface Connector {

    public abstract ResultSet executeReadQuery(String query);

    public abstract ResultSet executeUpdateQuery(String query);

}