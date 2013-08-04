package business.applicationservice.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import util.Parameter;

import presentation.controller.ApplicationService;

class ADISysServiceMethodSelector implements ServiceMethodSelector {

	private final ApplicationService as;
	
	public ADISysServiceMethodSelector(ApplicationService as) {
		this.as = as;
	}

	public Object invokeServiceMethod(String serviceName, Parameter parameter) {
		Object result = null;
		try {
			
			//TODO codice temporaneo. necessario restyling
			Class<?> asClass = as.getClass();
			String asMethodInString = ApplicationServiceSelector.getServiceMethod(serviceName);
			Method asMethod = asClass.getMethod(asMethodInString, Parameter.class);
			result = asMethod.invoke(as, parameter);
			
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
