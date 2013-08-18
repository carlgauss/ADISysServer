package presentation.controller;

import business.applicationservice.factory.ApplicationServiceMethod;
import business.applicationservice.factory.ApplicationServiceMethodFactory;
import presentation.boundary.Boundary;
import presentation.boundary.factory.BoundaryFactory;
import business.applicationservice.factory.ApplicationServiceFactory;
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
		ApplicationService asClass = ApplicationServiceFactory.buildInstance(serviceName);
		ApplicationServiceMethod asMethod = ApplicationServiceMethodFactory.buildInstance(asClass);
		return asMethod.invoke(serviceName, parameter);
	}

    private static void dispatchGUI(String serviceName) {
        Boundary boundary = BoundaryFactory.buildInstance(serviceName);
        boundary.showWindow();
    }


}
