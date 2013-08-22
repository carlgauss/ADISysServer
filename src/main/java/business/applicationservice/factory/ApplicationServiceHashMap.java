package business.applicationservice.factory;

import business.applicationservice.exception.UnavaliableApplicationServiceException;
import business.applicationservice.exception.UnselectedServiceNameException;
import util.Entry;

import java.util.HashMap;
import java.util.Map;

class ApplicationServiceHashMap implements ApplicationServiceMap {
    private Map<String, Entry<String, String>> asMapDictionary = new HashMap<>();
    private String currentServiceName = null;

    @Override
    public void selectApplicationServiceBy(String serviceName) {
        currentServiceName = serviceName;
    }

    @Override
    public void setApplicationServiceValues(String applicationService, String serviceFunction) {
        if (isServiceNameSelected()) {
            Entry<String, String> newValues = new Entry<>(applicationService, serviceFunction);
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
        Entry<String, String> mappedEntry = mapCouple(serviceName);
        return mappedEntry.getFirstValue();
    }

    @Override
    public String getServiceMethod(String serviceName) {
        Entry<String, String> mappedEntry = mapCouple(serviceName);
        return mappedEntry.getSecondValue();
    }

    private Entry<String, String> mapCouple(String serviceName) {
        checkService(serviceName);
        return asMapDictionary.get(serviceName);
    }

    private void checkService(String serviceName) {
        if (!asMapDictionary.containsKey(serviceName)) {
            throw new UnavaliableApplicationServiceException(serviceName);
        }
    }
}
