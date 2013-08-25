package business.applicationservice.factory;


class ApplicationServiceSelector {

    private static final String PACKAGE_PATH_NAME = "business.applicationservice.";

    private static ApplicationServiceMap asMap = new ApplicationServiceHashMap();

    static {
        asMap.selectApplicationServiceBy("VisualizzaTuttiPazienti");
        asMap.setApplicationServiceValues("ApplicationServicePaziente", "getAll");

        asMap.selectApplicationServiceBy("InserisciPaziente");
        asMap.setApplicationServiceValues("ApplicationServicePaziente", "create");

        asMap.selectApplicationServiceBy("ModificaPaziente");
        asMap.setApplicationServiceValues("ApplicationServicePaziente", "update");

        asMap.selectApplicationServiceBy("VisualizzaTuttiInfermieri");
        asMap.setApplicationServiceValues("ApplicationServiceInfermiere", "getAll");

        asMap.selectApplicationServiceBy("InserisciInfermiere");
        asMap.setApplicationServiceValues("ApplicationServiceInfermiere", "create");

        asMap.selectApplicationServiceBy("ModificaInfermiere");
        asMap.setApplicationServiceValues("ApplicationServiceInfermiere", "update");

        asMap.selectApplicationServiceBy("VisualizzaTuttiInterventi");
        asMap.setApplicationServiceValues("ApplicationServiceIntervento", "getAll");

        asMap.selectApplicationServiceBy("InserisciIntervento");
        asMap.setApplicationServiceValues("ApplicationServiceIntervento", "create");

        asMap.selectApplicationServiceBy("ModificaIntervento");
        asMap.setApplicationServiceValues("ApplicationServiceIntervento", "update");

        asMap.selectApplicationServiceBy("CancellaIntervento");
        asMap.setApplicationServiceValues("ApplicationServiceIntervento", "delete");

        asMap.selectApplicationServiceBy("VerificaOperazione");
        asMap.setApplicationServiceValues("ApplicationServiceOperazione", "checkOperazione");

        asMap.selectApplicationServiceBy("PrelevaPianificazione");
        asMap.setApplicationServiceValues("ApplicationServicePianificazione", "showPianificazione");

        asMap.selectApplicationServiceBy("EsportaPianificazione");
        asMap.setApplicationServiceValues("ApplicationServicePianificazione", "export");

        asMap.selectApplicationServiceBy("ElencaFileJournaling");
        asMap.setApplicationServiceValues("ApplicationServiceJournaling", "getFileList");

        asMap.selectApplicationServiceBy("AnalizzaJournaling");
        asMap.setApplicationServiceValues("ApplicationServiceJournaling", "importFile");

        //TODO implementare altre funzionalità
    }

    private ApplicationServiceSelector() {

    }

    public static String getApplicationService(String serviceName) {
        return PACKAGE_PATH_NAME + asMap.getApplicationService(serviceName);
    }

    public static String getServiceMethod(String serviceName) {
        return asMap.getServiceMethod(serviceName);
    }
}
