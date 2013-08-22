package presentation.controller;

import utility.Parameter;

class ADISysFC implements FrontController {

    public Object processRequest(String request, Parameter parameter) {
        ApplicationController ac = ApplicationControllerFactory.getApplicationController();
        return ac.handleRequest(request, parameter);
    }

}
