package business.applicationservice.factory;

import java.util.HashMap;
import java.util.Map;

import business.applicationservice.exception.UnavaliableApplicationServiceException;
import business.applicationservice.exception.UnselectedServiceNameException;

import util.Couple;

/*
   This class maps the service names (aka use case names) invoked in any boundary class
   with the corresponding method of an application service.
    This class is used to instantiate, at run time, the right application service
     responsible for the execution of the service.
     Then, within it, is invoked at run time the right method
     that will take care of the service demanded.
 */
class ApplicationServiceMapper {
	private Map<String, Couple<String, String>> asMapDictionary = new HashMap<>();
	private String currentServiceName = null;
	
	public void selectApplicationServiceBy(String serviceName) {
		currentServiceName = serviceName;
	}
	
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

	public String getApplicationService(String serviceName) {
		Couple<String, String> mappedCouple = mapCouple(serviceName);
		return mappedCouple.getFirstValue();
	}

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
