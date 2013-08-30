package presentation.controller;

import business.transfer.Parameter;

class ADISysFC implements FrontController {

    public Object processRequest(String request, Parameter parameter) {
        ApplicationController ac = ApplicationControllerFactory.getApplicationController();
        return ac.handleRequest(request, parameter);
    }

}
