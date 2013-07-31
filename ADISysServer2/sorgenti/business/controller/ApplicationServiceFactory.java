package business.controller;

import presentation.controller.ApplicationService;

public class ApplicationServiceFactory {

    private ApplicationServiceFactory() {
	
    }

    private static final String PACKAGE_PATH_NAME = "business.controller.";
    
    public static ApplicationService buildIstance(String serviceName) {
	ApplicationService as = null;
	
	try {
	    String canonicalClassName = 
		    ApplicationServiceSelector.getApplicationService(serviceName);
	    
	    Class<?> asClass = Class.forName(canonicalClassName);
	    as = (ApplicationService) asClass.newInstance();
	    
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (InstantiationException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	}
	
	return as;
    }
    
}
