package business.applicationservice.factory;

import business.applicationservice.factory.xml.XMLApplicationServiceMapper;



class ApplicationServiceSelector {

	private static final String PACKAGE_PATH_NAME = "business.applicationservice.";

	private static ApplicationServiceMap asMap = new ApplicationServiceHashMap();

	static {
		XMLApplicationServiceMapper.map(asMap);
	}

	private ApplicationServiceSelector() {

	}

	public static String getApplicationService(String serviceName) {
        return PACKAGE_PATH_NAME + asMap.getApplicationService(serviceName);
	}

	public static String getServiceMethod(String serviceName) {
		return asMap.getServiceMethod(serviceName);
	}
}
