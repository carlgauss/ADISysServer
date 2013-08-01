package presentation.controller;

import business.controller.ApplicationServiceFactory;
import business.controller.ServiceFunctionSelector;
import business.controller.ServiceFunctionSelectorFactory;
import util.Parameter;

class ADISysApplicationController implements ApplicationController {

	public Object handleRequest(String serviceName, Parameter parameter) {
		ApplicationService as = ApplicationServiceFactory.buildInstance(serviceName);
		ServiceFunctionSelector serviceFunctionSelector = ServiceFunctionSelectorFactory.buildInstance(as);
		return serviceFunctionSelector.invokeServiceFunction(serviceName, parameter);
	}


}
