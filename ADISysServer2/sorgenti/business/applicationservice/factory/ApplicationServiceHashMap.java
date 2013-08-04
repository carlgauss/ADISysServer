package business.applicationservice.factory;

import java.util.HashMap;
import java.util.Map;

import business.applicationservice.exception.UnavaliableApplicationServiceException;
import business.applicationservice.exception.UnselectedServiceNameException;

import util.Couple;

class ApplicationServiceHashMap implements ApplicationServiceMap {
	private Map<String, Couple<String, String>> asMapDictionary = new HashMap<>();
	private String currentServiceName = null;
	
	@Override
	public void selectApplicationServiceBy(String serviceName) {
		currentServiceName = serviceName;
	}
	
	@Override
	public void setApplicationServiceValues(String applicationService, String serviceFunction) {
		if (isServiceNameSelected()) {
			Couple<String, String> newValues = new Couple<>(applicationService, serviceFunction);
			asMapDictionary.put(currentServiceName, newValues);

		} else {
			throw new UnselectedServiceNameException();
		}
	}
	
	private boolean isServiceNameSelected() {
		return currentServiceName != null;
	}

	@Override
	public String getApplicationService(String serviceName) {
		Couple<String, String> mappedCouple = mapCouple(serviceName);
		return mappedCouple.getFirstValue();
	}

	@Override
	public String getServiceMethod(String serviceName) {
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
