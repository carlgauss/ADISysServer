package presentation.controller.test;

import business.applicationservice.ApplicationServicePaziente;
import business.applicationservice.exception.UnavaliableApplicationServiceException;
import mockit.Mockit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import presentation.controller.ApplicationController;
import presentation.controller.ApplicationControllerFactory;

import static org.junit.Assert.fail;

//import presentation.boundary.deprecated.SchermataPazienti;

public class ApplicationControllerTest {

    private static ApplicationController ac = null;

    @Before
    public void setUp() throws Exception {
        ac = ApplicationControllerFactory.buildInstance();
        Mockit.setUpMock(ApplicationServicePaziente.class, ApplicationServicePazienteStub.class);
        //Mockit.setUpMock(SchermataPazienti.class, SchermataPazientiStub.class);
    }

    @After
    public void tearDown() throws Exception {
        ac = null;
    }

    @Test
    public void testHandleRequest() {
        System.out.println("---Execute Report---");
        ac.handleRequest("InserisciPaziente", null);
        ac.handleRequest("ModificaPaziente", null);
    }

    @Test
    public void testMostra() {
        System.out.println("---DispatchGUI Report---");
        ac.handleRequest("MostraSchermataPazienti", null);
        try {
            ac.handleRequest("Mostra", null);
            fail("FAIL \"Mostra\" service name not refused by regex");
        } catch (UnavaliableApplicationServiceException e) {
            System.out.println("OK \"Mostra\" service name refused by regex");
        }
    }

}
