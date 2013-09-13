package business.applicationservice.factory;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
public class ApplicationServiceSelectorTest {

    private static final String PACKAGE_PATH_NAME = "business.applicationservice.";

    @Test
    public void testGetApplicationServiceGetAllPazienti() throws Exception {
        String serviceName = "VisualizzaTuttiPazienti";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServicePaziente"));
    }

    @Test
    public void testGetApplicationServiceCreatePazienti() throws Exception {
        String serviceName = "InserisciPaziente";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServicePaziente"));
    }

    @Test
    public void testGetApplicationServiceUpdatePaziente() throws Exception {
        String serviceName = "ModificaPaziente";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServicePaziente"));
    }

    @Test
    public void testGetApplicationServiceGetAllInfermieri() throws Exception {
        String serviceName = "VisualizzaTuttiInfermieri";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceInfermiere"));
    }

    @Test
    public void testGetApplicationServiceCreateInfermiere() throws Exception {
        String serviceName = "InserisciInfermiere";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceInfermiere"));
    }

    @Test
    public void testGetApplicationServiceUpdateInfermiere() throws Exception {
        String serviceName = "ModificaInfermiere";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceInfermiere"));
    }

    @Test
    public void testGetApplicationServiceGetAllInterventi() throws Exception {
        String serviceName = "VisualizzaTuttiInterventi";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceIntervento"));
    }

    @Test
    public void testGetApplicationServiceCreateIntervento() throws Exception {
        String serviceName = "InserisciIntervento";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceIntervento"));
    }


    @Test
    public void testGetApplicationServiceUpdateIntervento() throws Exception {
        String serviceName = "ModificaIntervento";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceIntervento"));
    }

    @Test
    public void testGetApplicationServiceDeleteIntervento() throws Exception {
        String serviceName = "CancellaIntervento";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceIntervento"));
    }

    @Test
    public void testGetApplicationServiceCheckOperazione() throws Exception {
        String serviceName = "VerificaOperazione";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceOperazione"));
    }

    @Test
    public void testGetApplicationServiceShowPianificazione() throws Exception {
        String serviceName = "PrelevaPianificazione";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServicePianificazione"));
    }

    @Test
    public void testGetApplicationServiceExportPianificazione() throws Exception {
        String serviceName = "EsportaPianificazione";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServicePianificazione"));
    }

    @Test
    public void testGetApplicationServiceGetFileJournaling() throws Exception {
        String serviceName = "ElencaFileJournaling";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceJournaling"));
    }

    @Test
    public void testGetApplicationServiceAnalizzaJournaling() throws Exception {
        String serviceName = "AnalizzaJournaling";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceJournaling"));
    }

    @Test
    public void testGetApplicationServiceAnalizzaGPS() throws Exception {
        String serviceName = "AnalizzaGPS";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceVerifica"));
    }

    @Test
    public void testGetApplicationServiceAnalizzaAccelerometro() throws Exception {
        String serviceName = "AnalizzaAccelerometro";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceVerifica"));
    }

    @Test
    public void testGetApplicationServiceSearchIntervento() throws Exception {
        String serviceName = "CercaIntervento";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceIntervento"));
    }

    @Test
    public void testGetApplicationServiceGetAllPatologie() throws Exception {
        String serviceName = "VisualizzaTuttePatologie";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServicePatologia"));
    }

    @Test
    public void testGetApplicationServiceCheckPatologiaCode() throws Exception {
        String serviceName = "VerificaCodicePatologia";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServicePatologia"));
    }

    @Test
    public void testGetApplicationServiceUpdateAllPatologie() throws Exception {
        String serviceName = "AggiornaTutteLePatologie";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServicePatologia"));
    }

    @Test
    public void testGetApplicationServiceInitialCheck() throws Exception {
        String serviceName = "VerificaIniziale";
        String pathASclass = ApplicationServiceSelector.getApplicationService(serviceName);
        assertThat(pathASclass, equalTo(PACKAGE_PATH_NAME + "ApplicationServiceGeneral"));
    }






    @Test
    public void testGetServiceMethodGetAllPazienti() throws Exception {
        String serviceName = "VisualizzaTuttiPazienti";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("getAll"));
    }


    @Test
    public void testGetServiceMethodCreatePaziente() throws Exception {
        String serviceName = "InserisciPaziente";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("create"));
    }

    @Test
    public void testGetServiceMethodUpdatePaziente() throws Exception {
        String serviceName = "ModificaPaziente";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("update"));
    }


    @Test
    public void testGetServiceMethodGetAllInfermieri() throws Exception {
        String serviceName = "VisualizzaTuttiInfermieri";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("getAll"));
    }


    @Test
    public void testGetServiceMethodCreateInfermiere() throws Exception {
        String serviceName = "InserisciInfermiere";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("create"));
    }

    @Test
    public void testGetServiceMethodUpdateInfermiere() throws Exception {
        String serviceName = "ModificaInfermiere";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("update"));
    }


    @Test
    public void testGetServiceMethodGetAllInterventi() throws Exception {
        String serviceName = "VisualizzaTuttiInterventi";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("getAll"));
    }


    @Test
    public void testGetServiceMethodCreateIntervento() throws Exception {
        String serviceName = "InserisciIntervento";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("create"));
    }

    @Test
    public void testGetServiceMethodUpdateIntervento() throws Exception {
        String serviceName = "ModificaIntervento";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("update"));
    }

    @Test
    public void testGetServiceMethodDeleteIntervento() throws Exception {
        String serviceName = "CancellaIntervento";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("delete"));
    }

    @Test
    public void testGetServiceMethodCheckOperazione() throws Exception {
        String serviceName = "VerificaOperazione";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("checkOperazione"));
    }

    @Test
    public void testGetServiceMethodShowPianificazione() throws Exception {
        String serviceName = "PrelevaPianificazione";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("showPianificazione"));
    }

    @Test
    public void testGetServiceMethodEsportaPianificazione() throws Exception {
        String serviceName = "EsportaPianificazione";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("export"));
    }

    @Test
    public void testGetServiceMethodGetFileJournaling() throws Exception {
        String serviceName = "ElencaFileJournaling";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("getFileList"));
    }

    @Test
    public void testGetServiceMethodAnalizzajournaling() throws Exception {
        String serviceName = "AnalizzaJournaling";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("importFile"));
    }


    @Test
    public void testGetServiceMethodAnalizzaGPS() throws Exception {
        String serviceName = "AnalizzaGPS";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("deriveGPS"));
    }

    @Test
    public void testGetServiceMethodAnalizzaAccelerometro() throws Exception {
        String serviceName = "AnalizzaAccelerometro";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("deriveAccelerometro"));
    }

    @Test
    public void testGetServiceMethodCercaIntervento() throws Exception {
        String serviceName = "CercaIntervento";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("queryIntervento"));
    }

    @Test
    public void testGetServiceMethodGetAllPatologie() throws Exception {
        String serviceName = "VisualizzaTuttePatologie";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("getAll"));
    }

    @Test
    public void testGetServiceMethodCheckPatologiaCode() throws Exception {
        String serviceName = "VerificaCodicePatologia";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("checkCodice"));
    }

    @Test
    public void testGetServiceMethodUpdateaAllPatologia() throws Exception {
        String serviceName = "AggiornaTutteLePatologie";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("updateAll"));
    }

    @Test
    public void testGetServiceMethodInitialCheck() throws Exception {
        String serviceName = "VerificaIniziale";
        String serviceMethod = ApplicationServiceSelector.getServiceMethod(serviceName);
        assertThat(serviceMethod, equalTo("check"));
    }

}
