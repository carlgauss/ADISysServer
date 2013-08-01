package business.controller;

import java.lang.reflect.InvocationTargetException;

import util.Parameter;

import presentation.controller.ApplicationService;

class ADISysServiceFunctionSelector implements ServiceFunctionSelector {

	private final ApplicationService as;
	
	public ADISysServiceFunctionSelector(ApplicationService as) {
		this.as = as;
	}

	public Object invokeServiceFunction(String serviceName, Parameter parameter) {
		Object result = null;
		try {
			//TODO codice temporaneo. necessario restyling
			result = as.getClass().getMethod(ApplicationServiceSelector.getServiceFuncion(serviceName), Object.class).invoke(as, parameter);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return result;
	}
}
