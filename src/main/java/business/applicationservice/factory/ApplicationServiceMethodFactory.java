package business.applicationservice.factory;

import presentation.controller.ApplicationService;

public class ApplicationServiceMethodFactory {

    private ApplicationServiceMethodFactory() {

    }

    public static ApplicationServiceMethod buildInstance(ApplicationService as) {
        return new ADISysApplicationServiceMethod(as);
    }

}
