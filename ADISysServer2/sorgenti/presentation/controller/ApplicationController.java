package presentation.controller;

import util.Parameter;

public interface ApplicationController {
    public Object handleRequest(String request, Parameter parameter);

}
