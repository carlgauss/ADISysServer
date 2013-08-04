package business.applicationservice.factory;

import util.Parameter;

public interface ServiceMethodSelector {
	public Object invokeServiceMethod(String serviceName, Parameter parameter);
}