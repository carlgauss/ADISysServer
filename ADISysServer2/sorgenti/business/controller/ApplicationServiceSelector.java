package business.controller;

import java.util.HashMap;
import java.util.Map;

class ApplicationServiceSelector {

    static Map<String, String[]> ASList = new HashMap<String, String[]>();

    private static final int APPLICATION_SERVICE_NAME = 0;
    private static final int METHOD_NAME = 1;
    
    static {
	/* Inserimento delle corrispondenze tra le richieste
	 * effettuate tramite le classi boundary e gli
	 * ApplicationServices */
	ASList.put("InserisciPaziente", new String[]{"ApplicationServicePaziente", "create"});
	ASList.put("ModificaPaziente", new String[]{"ApplicationServicePaziente", "update"});
    }

    private ApplicationServiceSelector() {

    }

    public static String getApplicationService(String serviceName) {
	String applicationService = null;
	
	if (serviceExists(serviceName)) {
	    applicationService = mapApplicationServiceNameFrom(serviceName);
	} else {
	    throw new UnavaliableApplicationServiceException(serviceName);
	}
	
	return applicationService;
    }
    
    
    private static boolean serviceExists(String serviceName){
	return ASList.containsKey(serviceName);
    }
    
    private static String mapApplicationServiceNameFrom(String serviceName) {
	return ASList.get(serviceName)[APPLICATION_SERVICE_NAME];
    }
}
