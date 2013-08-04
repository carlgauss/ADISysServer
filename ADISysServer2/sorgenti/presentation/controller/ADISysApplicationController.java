package presentation.controller;

import business.applicationservice.factory.ServiceMethodSelector;
import presentation.boundary.Boundary;
import presentation.boundary.factory.BoundaryFactory;
import business.applicationservice.factory.ApplicationServiceFactory;
import business.applicationservice.factory.ServiceFunctionSelectorFactory;
import util.Parameter;

class ADISysApplicationController implements ApplicationController {
	
	//TODO decidere quali comandi utilizzare
	private static String SHOW_SYNTAX = "Mostra[a-zA-Z]+";

	public Object handleRequest(String serviceName, Parameter parameter) {
		Object result = null;
		
		if (serviceName.matches(SHOW_SYNTAX)) {
            dispatchGUI(serviceName);
		} else {
			result = execute(serviceName, parameter);
		}
		
		return result;
	}

	private Object execute(String serviceName, Parameter parameter) {
		ApplicationService as = ApplicationServiceFactory.buildInstance(serviceName);
		ServiceMethodSelector serviceMethodSelector = ServiceFunctionSelectorFactory.buildInstance(as);
		return serviceMethodSelector.invokeServiceMethod(serviceName, parameter);
	}

    private static void dispatchGUI(String serviceName) {
        Boundary boundary = BoundaryFactory.buildInstance(serviceName);
        boundary.showWindow();
    }


}
