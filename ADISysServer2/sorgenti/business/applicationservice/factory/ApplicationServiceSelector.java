package business.applicationservice.factory;



class ApplicationServiceSelector {

	private static final String PACKAGE_PATH_NAME = "business.applicationservice.";

	private static ApplicationServiceMapper asMap = new ApplicationServiceMapper();

	static {
		/*
		 * Inserimento delle corrispondenze tra le richieste effettuate tramite
		 * le classi boundary e gli ApplicationServices
		 */
		asMap.selectApplicationServiceBy("InserisciPaziente");
		asMap.setApplicationServiceValues("ApplicationServicePaziente", "create");
		
		asMap.selectApplicationServiceBy("ModificaPaziente");
		asMap.setApplicationServiceValues("ApplicationServicePaziente", "update");
	}

	private ApplicationServiceSelector() {

	}

	public static String getApplicationService(String serviceName) {
		String applicationServiceCanonicalName = PACKAGE_PATH_NAME
				+ asMap.getApplicationService(serviceName);

		return applicationServiceCanonicalName;
	}

	public static String getServiceFunction(String serviceName) {
		return asMap.getServiceFunction(serviceName);
	}
}
