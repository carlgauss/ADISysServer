package business.controller.test;import static org.junit.Assert.*;import org.junit.After;import org.junit.Before;import org.junit.Test;import business.controller.*;import business.controller.selector.ApplicationServiceFactory;import presentation.controller.ApplicationService;public class ApplicationServiceFactoryTest {	@Before	public void setUp() throws Exception {	}	@After	public void tearDown() throws Exception {	}	@Test	public void testBuildIstance() {		String msg = null;		ApplicationService as = ApplicationServiceFactory				.buildInstance("InserisciPaziente");		msg = as.getClass().getName() + " instead of "				+ "ApplicationServicePaziente";		assertTrue(msg, as instanceof ApplicationServicePaziente);		try {			ApplicationServiceFactory.buildInstance("UnknownCommand");			fail("UnavaliableApplicationServiceException not thrown");		} catch (UnavaliableApplicationServiceException e) {			assertTrue(false);		}	}}