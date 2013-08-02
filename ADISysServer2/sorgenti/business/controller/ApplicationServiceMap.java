package business.controller;

import java.util.HashMap;
import java.util.Map;

import util.Couple;

class ApplicationServiceMap {
	private Map<String, Couple<String, String>> asMapDictionary = new HashMap<String, Couple<String, String>>();

	private String currentServiceName = null;
	
	public void selectApplicationServiceBy(String serviceName) {
		currentServiceName = serviceName;
	}
	
	public void setApplicationServiceValues(String applicationService, String serviceFunction) {
		if (isServiceNameSelected()) {
			Couple<String, String> newValues = 
					new Couple<String, String>(applicationService, serviceFunction);
			
			asMapDictionary.put(currentServiceName, newValues);
			
		} else {
			throw new UnselectedServiceNameException();
		}
	}
	
	private boolean isServiceNameSelected() {
		return currentServiceName != null;
	}

	public String getApplicationService(String serviceName) {
		Couple<String, String> mappedCouple = mapCouple(serviceName);
		return mappedCouple.getFirstValue();
	}

	public String getServiceFunction(String serviceName) {
		Couple<String, String> mappedCouple = mapCouple(serviceName);
		return mappedCouple.getSecondValue();
	}

    private Couple<String, String> mapCouple(String serviceName) {
        checkService(serviceName);
        return asMapDictionary.get(serviceName);
    }

    private void checkService(String serviceName) {
        if (!asMapDictionary.containsKey(serviceName)) {
            throw new UnavaliableApplicationServiceException(serviceName);
        }
    }
}
