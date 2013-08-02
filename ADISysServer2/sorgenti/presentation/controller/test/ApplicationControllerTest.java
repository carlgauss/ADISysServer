package presentation.controller.test;

import static org.junit.Assert.*;

import mockit.Mockit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.applicationservice.ApplicationServicePaziente;

import presentation.controller.ApplicationController;
import presentation.controller.ApplicationControllerFactory;

public class ApplicationControllerTest {

	private static ApplicationController ac = null;
	
	@Before
	public void setUp() throws Exception {
		ac = ApplicationControllerFactory.buildInstance();
		Mockit.setUpMock(ApplicationServicePaziente.class, ApplicationServicePazienteStub.class);
	}

	@After
	public void tearDown() throws Exception {
		ac = null;
	}

	@Test
	public void testHandleRequest() {
		ac.handleRequest("InserisciPaziente", null);
		ac.handleRequest("ModificaPaziente", null);
		}

    @Test
    public void testMostra() {
        ac.handleRequest("Mostra", null);
        ac.handleRequest("MostraSchermata", null);
        ac.handleRequest("Mostra schermata", null);
        ac.handleRequest("Mostra schermata pazienti", null);
        ac.handleRequest("Mostra  scherma ta pazienti ", null);
    }

}
