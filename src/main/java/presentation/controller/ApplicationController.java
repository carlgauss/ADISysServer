package presentation.controller;

import business.transfer.Parameter;

public interface ApplicationController {
    public Object handleRequest(String serviceName, Parameter parameter);

}
