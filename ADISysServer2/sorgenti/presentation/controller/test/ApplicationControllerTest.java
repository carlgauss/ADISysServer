package presentation.controller.test;

import static org.junit.Assert.*;

import mockit.Mockit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.controller.ApplicationServicePaziente;

import presentation.controller.ApplicationController;
import presentation.controller.ApplicationControllerFactory;

public class ApplicationControllerTest {

	private static ApplicationController ac = null;
	
	@Before
	public void setUp() throws Exception {
		ac = ApplicationControllerFactory.buildInstance();
	}

	@After
	public void tearDown() throws Exception {
		ac = null;
	}

	@Test
	public void testHandleRequest() {
		new ApplicationServicePazienteStub();
		ac.handleRequest("InserisciPaziente", null);
		assertTrue(true);
		}

}
