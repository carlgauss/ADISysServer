package business.applicationservice.factory;
import business.applicationservice.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import presentation.controller.ApplicationService;

import static org.junit.Assert.assertTrue;
public class ApplicationServiceFactoryTest {

    ApplicationService as;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetAsPazienteByGetAll() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("VisualizzaTuttiPazienti");
        assertTrue(as instanceof ApplicationServicePaziente);
    }

    @Test
    public void testGetAsPazienteByCreate() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("InserisciPaziente");
        assertTrue(as instanceof ApplicationServicePaziente);
    }

    @Test
    public void testGetAsPazienteByUpdate() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("ModificaPaziente");
        assertTrue(as instanceof ApplicationServicePaziente);
    }


    @Test
    public void testGetAsInfermiereByGetAll() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("VisualizzaTuttiInfermieri");
        assertTrue(as instanceof ApplicationServiceInfermiere);
    }

    @Test
    public void testGetAsInfermiereByCreate() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("InserisciInfermiere");
        assertTrue(as instanceof ApplicationServiceInfermiere);
    }

    @Test
    public void testGetAsInfermiereByUpdate() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("ModificaInfermiere");
        assertTrue(as instanceof ApplicationServiceInfermiere);
    }


    @Test
    public void testGetAsInterventoByGetAll() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("VisualizzaTuttiInterventi");
        assertTrue(as instanceof ApplicationServiceIntervento);
    }

    @Test
    public void testGetAsInterventoByCreate() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("InserisciIntervento");
        assertTrue(as instanceof ApplicationServiceIntervento);
    }

    @Test
    public void testGetAsInterventoByUpdate() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("ModificaIntervento");
        assertTrue(as instanceof ApplicationServiceIntervento);
    }

    @Test
    public void testGetAsInterventoByDelete() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("CancellaIntervento");
        assertTrue(as instanceof ApplicationServiceIntervento);
    }


    @Test
    public void testGetAsOperazioneByVerifica() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("VerificaOperazione");
        assertTrue(as instanceof ApplicationServiceOperazione);
    }

    @Test
    public void testGetAsPianificazioneByPreleva() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("PrelevaPianificazione");
        assertTrue(as instanceof ApplicationServicePianificazione);
    }

    @Test
    public void testGetAsPianificazioneByEsporta() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("EsportaPianificazione");
        assertTrue(as instanceof ApplicationServicePianificazione);
    }

    @Test
    public void testGetAsJournalingByElenca() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("ElencaFileJournaling");
        assertTrue(as instanceof ApplicationServiceJournaling);
    }

    @Test
    public void testGetAsJournalingByAnalizza() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("AnalizzaJournaling");
        assertTrue(as instanceof ApplicationServiceJournaling);
    }

    @Test
    public void testGetAsVerificaByAnalizzaGPS() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("AnalizzaGPS");
        assertTrue(as instanceof ApplicationServiceVerifica);
    }

    @Test
    public void testGetAsVerificaByAnalizzaAccelerometro() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("AnalizzaAccelerometro");
        assertTrue(as instanceof ApplicationServiceVerifica);
    }

    @Test
    public void testGetAsInterventoByCerca() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("CercaIntervento");
        assertTrue(as instanceof ApplicationServiceIntervento);
    }

    @Test
    public void testGetAsPatologiaByGetAll() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("VisualizzaTuttePatologie");
        assertTrue(as instanceof ApplicationServicePatologia);
    }

    @Test
    public void testGetAsPatologiaByVerificaCodice() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("VerificaCodicePatologia");
        assertTrue(as instanceof ApplicationServicePatologia);
    }

    @Test
    public void testGetAsPatologiaByAggiorna() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("AggiornaTutteLePatologie");
        assertTrue(as instanceof ApplicationServicePatologia);
    }

    @Test
    public void testGetAsGeneralByVerificaIniziale() throws Exception {
        as = ApplicationServiceFactory.getApplicationService("VerificaIniziale");
        assertTrue(as instanceof ApplicationServiceGeneral);
    }



}
