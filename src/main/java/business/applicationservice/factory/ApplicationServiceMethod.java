package business.applicationservice.factory;

import business.applicationservice.exception.CommonInvalidFieldException;
import utility.Parameter;

public interface ApplicationServiceMethod {
    public Object invoke(String serviceName, Parameter parameter) throws CommonInvalidFieldException;
}