package presentation.controller;

import business.applicationservice.factory.ApplicationServiceFactory;
import business.applicationservice.factory.ServiceFunctionSelector;
import business.applicationservice.factory.ServiceFunctionSelectorFactory;
import util.Parameter;

class ADISysApplicationController implements ApplicationController {

	public Object handleRequest(String serviceName, Parameter parameter) {
		ApplicationService as = ApplicationServiceFactory.buildInstance(serviceName);
		ServiceFunctionSelector serviceFunctionSelector = ServiceFunctionSelectorFactory.buildInstance(as);
		return serviceFunctionSelector.invokeServiceFunction(serviceName, parameter);
	}


}
