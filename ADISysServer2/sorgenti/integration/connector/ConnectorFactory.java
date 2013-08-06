package integration.connector;

public class ConnectorFactory {
	private ConnectorFactory() {
		
	}
	
	public static Connector buildIstance() {
		return new HQSQLConnector();
	}
}
