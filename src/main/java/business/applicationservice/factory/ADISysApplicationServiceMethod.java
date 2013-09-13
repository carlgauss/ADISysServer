package business.applicationservice.factory;

import business.applicationservice.exception.CommonException;
import presentation.controller.ApplicationService;
import business.transfer.Parameter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ADISysApplicationServiceMethod implements ApplicationServiceMethod {

    private final ApplicationService as;

    public ADISysApplicationServiceMethod(ApplicationService as) {
        this.as = as;
    }

    public Object invoke(String serviceName, Parameter parameter) throws CommonException {
        Object result = null;
        try {
            Class<?> asClass = as.getClass();
            String asMethodInString = ApplicationServiceSelector.getServiceMethod(serviceName);
            Method asMethod = asClass.getMethod(asMethodInString, Parameter.class);
            result = asMethod.invoke(as, parameter);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            Class<?> causeClass = cause.getClass();
            if (CommonException.class.isAssignableFrom(causeClass)) {
                CommonException commonCause = (CommonException) cause;
                throw commonCause;
            } else {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return result;
    }
}
