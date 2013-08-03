package integration.dao;

public class DAOFactory {
	
	private static final String DAO_PATH = "integration.dao.";

	private DAOFactory() {
	}

	public static DAO buildInstance(String daoName) {
		String daoCanonicalName = getDAOCanonicalName(daoName);
		Class<?> daoClass = getDAOClass(daoCanonicalName);
		return getDAOInstance(daoClass);
	}
	
	private static String getDAOCanonicalName(String daoName) {
		return DAO_PATH + daoName;
	}
	
	private static Class<?> getDAOClass(String daoCanonicalName) {
		Class<?> daoClass = null;
		
		try {
			daoClass = Class.forName(daoCanonicalName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return daoClass;
	}
	
	private static DAO getDAOInstance(Class<?> daoClass) {
		DAO daoInstance = null;
		
		try {
			daoInstance = (DAO) daoClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return daoInstance;
	}
}