package business.controller.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.controller.*;


import presentation.controller.ApplicationService;

public class ApplicationServiceFactoryTest {

    @Before
    public void setUp() throws Exception {
	
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testBuildIstance() {
	String msg = null;
	
	ApplicationService as = ApplicationServiceFactory.buildIstance("InserisciPaziente");
	
	msg = as.getClass().getName() + " instead of " + "ApplicationServicePaziente";
	
	assertTrue(msg, as instanceof ApplicationServicePaziente);
	
	try {
	    ApplicationServiceFactory.buildIstance("UnknownCommand");
	    fail("UnavaliableApplicationServiceException not thrown");
	    
	} catch (UnavaliableApplicationServiceException e) {
	    
	}
    }

}
