package presentation.controller;

import business.transfer.Parameter;

public interface FrontController {

    public Object processRequest(String request, Parameter parameter);

}
