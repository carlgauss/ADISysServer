package presentation.controller;

import business.applicationservice.exception.CommonException;
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

        try {
            if (serviceName.matches(SHOW_SYNTAX)) {
                result = dispatchGUI(serviceName, parameter);
            } else {
                result = execute(serviceName, parameter);
            }
            if (result == null) {
                result = true;
            }
        } catch (CommonException e) {
            e.reportException();
        }

        return result;
	}

	private Object execute(String serviceName, Parameter parameter) throws CommonException {
		ApplicationService asClass = ApplicationServiceFactory.buildInstance(serviceName);
		ApplicationServiceMethod asMethod = ApplicationServiceMethodFactory.buildInstance(asClass);
		return asMethod.invoke(serviceName, parameter);
	}

    private static Object dispatchGUI(String serviceName, Parameter parameter) {
        Boundary boundary = BoundaryFactory.buildInstance(serviceName, parameter);
        return boundary.showWindow(parameter);
    }


}
