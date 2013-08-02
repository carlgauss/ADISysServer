package business.applicationservice.factory;

import util.Parameter;

public interface ServiceFunctionSelector {
	public Object invokeServiceFunction(String serviceName, Parameter parameter);
}