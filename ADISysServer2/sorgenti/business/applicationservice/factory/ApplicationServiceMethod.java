package business.applicationservice.factory;

import util.Parameter;

public interface ApplicationServiceMethod {
	public Object invoke(String serviceName, Parameter parameter);
}