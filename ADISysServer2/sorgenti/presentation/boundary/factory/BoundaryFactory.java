package presentation.boundary.factory;

import presentation.boundary.Boundary;

public class BoundaryFactory {
	private static final String BOUNDARY_PACKAGE_PATH = "presentation.boundary.";
	private static final String SERVICE_NAME_HEAD = "Mostra";
	private static final int BOUNDARY_NAME_START_POSITION = SERVICE_NAME_HEAD.length();
	
	private BoundaryFactory() {
		
	}
	
	public static Boundary buildInstance(String serviceName) {
		Class<?> boundaryClass = getBoundaryClass(serviceName);
		return newBoundaryInstance(boundaryClass);
	}
	
	private static String getSimpleClassName(String serviceName) {
		return serviceName.substring(BOUNDARY_NAME_START_POSITION);
	}
	
	private static String getCanonicalClassName(String simpleClassName) {
		return BOUNDARY_PACKAGE_PATH + simpleClassName;
	}
	
	private static Class<?> getBoundaryClass(String serviceName) {
		String simpleClassName = getSimpleClassName(serviceName);
		String canonicalClassName = getCanonicalClassName(simpleClassName);
		
		Class<?> boundaryClass = null;
		
		try {
			boundaryClass = Class.forName(canonicalClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return boundaryClass;
	}
	
	private static Boundary newBoundaryInstance(Class<?> boundaryClass) {
		Boundary boundaryInstance = null;
		
		try {
			boundaryInstance = (Boundary) boundaryClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return boundaryInstance;
	}
}
