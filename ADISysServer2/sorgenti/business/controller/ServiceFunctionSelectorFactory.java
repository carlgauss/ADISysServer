package business.controller;

import presentation.controller.ApplicationService;

public class ServiceFunctionSelectorFactory {

    private ServiceFunctionSelectorFactory() {
	
    }
    
    public static ServiceFunctionSelector buildInstance(ApplicationService as) {
    	return new ADISysServiceFunctionSelector(as);
    }
    
}
