package presentation.controller;

import utility.Parameter;

class ADISysFrontController implements FrontController {

    public Object processRequest(String request, Parameter parameter) {
        ApplicationController ac = ApplicationControllerFactory.buildInstance();
        return ac.handleRequest(request, parameter);
    }

}
