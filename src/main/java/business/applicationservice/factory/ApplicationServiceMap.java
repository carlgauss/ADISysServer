package business.applicationservice.factory;

public interface ApplicationServiceMap {

    /*
       This class maps the service names (aka use case names) invoked in any boundary class
       with the corresponding method of an application service.
        This class is used to instantiate, at run time, the right application service
         responsible for the execution of the service.
         Then, within it, is invoked at run time the right method
         that will take care of the service demanded.
     */
    public abstract void selectApplicationServiceBy(String serviceName);

    public abstract void setApplicationServiceValues(String applicationService,
                                                     String serviceFunction);

    public abstract String getApplicationService(String serviceName);

    public abstract String getServiceMethod(String serviceName);

}