package presentation.controller;

import util.Parameter;

public interface FrontController {

    public Object processRequest(String request, Parameter parameter);
    
}
