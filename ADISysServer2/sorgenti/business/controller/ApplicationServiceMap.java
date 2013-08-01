package business.controller;

import java.util.HashMap;
import java.util.Map;

import util.Couple;

class ApplicationServiceMap {
	private Map<String, Couple<String, String>> asMapDictionary = new HashMap<String, Couple<String, String>>();

	public void put(String serviceName, String applicationService,
			String serviceFunction) {
		Couple<String, String> coupleTemp;

		coupleTemp = new Couple<String, String>(applicationService,
				serviceFunction);

		asMapDictionary.put(serviceName, coupleTemp);
	}

	private void checkService(String serviceName) {
		if (!asMapDictionary.containsKey(serviceName)) {
			throw new UnavaliableApplicationServiceException(serviceName);
		}
	}

	private Couple<String, String> mapCouple(String serviceName) {
		checkService(serviceName);
		return asMapDictionary.get(serviceName);
	}

	public String getApplicationService(String serviceName) {
		Couple<String, String> mappedCouple = mapCouple(serviceName);
		return mappedCouple.getFirstValue();
	}

	public String getServiceFunction(String serviceName) {
		Couple<String, String> mappedCouple = mapCouple(serviceName);
		return mappedCouple.getSecondValue();
	}
}
