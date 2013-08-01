package business.controller;

import util.Parameter;

public interface ServiceFunctionSelector {
	public Object invokeServiceFunction(String serviceName, Parameter parameter);
}