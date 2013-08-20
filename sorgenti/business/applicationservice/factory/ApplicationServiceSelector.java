package business.applicationservice.factory;



class ApplicationServiceSelector {

	private static final String PACKAGE_PATH_NAME = "business.applicationservice.";

	private static ApplicationServiceMap asMap = new ApplicationServiceHashMap();

	static {
        asMap.selectApplicationServiceBy("VisualizzaTuttiPazienti");
        asMap.setApplicationServiceValues("ApplicationServicePaziente", "getAll");

        asMap.selectApplicationServiceBy("InserisciPaziente");
        asMap.setApplicationServiceValues("ApplicationServicePaziente", "create");

        asMap.selectApplicationServiceBy("ModificaPaziente");
        asMap.setApplicationServiceValues("ApplicationServicePaziente", "update");

        asMap.selectApplicationServiceBy("VisualizzaTuttiInfermieri");
        asMap.setApplicationServiceValues("ApplicationServiceInfermiere", "getAll");

        asMap.selectApplicationServiceBy("VisualizzaTuttiInterventi");
        asMap.setApplicationServiceValues("ApplicationServiceIntervento", "getAll");

        //TODO implementare altre funzionalità
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
