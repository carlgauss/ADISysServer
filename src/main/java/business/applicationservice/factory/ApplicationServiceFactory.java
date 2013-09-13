package business.applicationservice.factory;

import presentation.controller.ApplicationService;

public class ApplicationServiceFactory {

    private ApplicationServiceFactory() {

    }

    public static ApplicationService getApplicationService(String serviceName) {
        ApplicationService as = null;

        try {
            String canonicalClassName = ApplicationServiceSelector.getApplicationService(serviceName);
            Class<?> asClass = Class.forName(canonicalClassName);
            as = (ApplicationService) asClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return as;
    }

}
