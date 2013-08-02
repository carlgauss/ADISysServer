package presentation.controller;

import business.applicationservice.factory.ApplicationServiceFactory;
import business.applicationservice.factory.ServiceFunctionSelector;
import business.applicationservice.factory.ServiceFunctionSelectorFactory;
import util.Parameter;

class ADISysApplicationController implements ApplicationController {
	
	private static String SHOW_SYNTAX = "Mostra[a-zA-Z]*";

	public Object handleRequest(String serviceName, Parameter parameter) {
		Object result = null;
		
		if (serviceName.matches(SHOW_SYNTAX)) {
			
		} else {
			result = execute(serviceName, parameter);
		}
		
		return result;
	}

	private Object execute(String serviceName, Parameter parameter) {
		ApplicationService as = ApplicationServiceFactory.buildInstance(serviceName);
		ServiceFunctionSelector serviceFunctionSelector = ServiceFunctionSelectorFactory.buildInstance(as);
		return serviceFunctionSelector.invokeServiceFunction(serviceName, parameter);
	}
}
