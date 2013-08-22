package presentation.controller;

import utility.Parameter;

public interface ApplicationController {
    public Object handleRequest(String serviceName, Parameter parameter);

}
