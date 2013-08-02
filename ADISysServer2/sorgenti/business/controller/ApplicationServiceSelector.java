package business.controller;

class ApplicationServiceSelector {

	private static final String PACKAGE_PATH_NAME = "business.controller.";

	static ApplicationServiceMap asList = new ApplicationServiceMap();

	static {
		/*
		 * Inserimento delle corrispondenze tra le richieste effettuate tramite
		 * le classi boundary e gli ApplicationServices
		 */
		asList.put("InserisciPaziente", "ApplicationServicePaziente", "create");
		asList.put("ModificaPaziente", "ApplicationServicePaziente", "update");
	}

	private ApplicationServiceSelector() {

	}

	public static String getApplicationService(String serviceName) {
		String applicationServiceCanonicalName = PACKAGE_PATH_NAME
				+ asList.getApplicationService(serviceName);

		return applicationServiceCanonicalName;
	}

	public static String getServiceFunction(String serviceName) {
		return asList.getServiceFunction(serviceName);
	}
}
