package business.applicationservice.factory;

import presentation.controller.ApplicationService;

public class ServiceFunctionSelectorFactory {

    private ServiceFunctionSelectorFactory() {
	
    }
    
    public static ServiceMethodSelector buildInstance(ApplicationService as) {
    	return new ADISysServiceMethodSelector(as);
    }
    
}
