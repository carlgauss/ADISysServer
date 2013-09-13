package presentation.controller;

import business.applicationservice.exception.CommonException;
import business.applicationservice.factory.ApplicationServiceFactory;
import business.applicationservice.factory.ApplicationServiceMethod;
import business.applicationservice.factory.ApplicationServiceMethodFactory;
import presentation.boundary.Boundary;
import presentation.boundary.factory.BoundaryFactory;
import business.transfer.Parameter;

class ADISysAC implements ApplicationController {


    private static String SHOW_GUI_SYNTAX = "Mostra[a-zA-Z]+";

    public Object handleRequest(String serviceName, Parameter parameter) {
        Object result = null;

        try {
            if (serviceName.matches(SHOW_GUI_SYNTAX)) {
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
        ApplicationService appServiceClass = ApplicationServiceFactory.getApplicationService(serviceName);
        ApplicationServiceMethod appServiceMethod = ApplicationServiceMethodFactory.getASMethod(appServiceClass);
        return appServiceMethod.invoke(serviceName, parameter);
    }

    private static Object dispatchGUI(String serviceName, Parameter parameter) {
        Boundary boundary = BoundaryFactory.getBoundary(serviceName, parameter);
        return boundary.showWindow(parameter);
    }


}
