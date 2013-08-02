package presentation.controller;

import business.controller.selector.ApplicationServiceFactory;
import business.controller.selector.ServiceFunctionSelector;
import business.controller.selector.ServiceFunctionSelectorFactory;
import util.Parameter;

class ADISysApplicationController implements ApplicationController {

	public Object handleRequest(String serviceName, Parameter parameter) {
		ApplicationService as = ApplicationServiceFactory.buildInstance(serviceName);
		ServiceFunctionSelector serviceFunctionSelector = ServiceFunctionSelectorFactory.buildInstance(as);
		return serviceFunctionSelector.invokeServiceFunction(serviceName, parameter);
	}


}
