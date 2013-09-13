package presentation.controller;
import business.transfer.Parameter;
import org.junit.After;
import org.junit.Before;
import presentation.boundary.Boundary;
import presentation.boundary.SchermataPatologia;

import static org.junit.Assert.assertTrue;
public class ADISysACTest {

    ApplicationController ac;
    Parameter parameter;

    @Before
    public void setUp() throws Exception {
        ac = ApplicationControllerFactory.getApplicationController();
        parameter = new Parameter();
    }

    @After
    public void tearDown() throws Exception {
        ac = null;
        parameter = null;
    }

    // TODO javafx problems
   // @Test
    public void testHandleRequest() throws Exception {
        ac.handleRequest("VerificaIniziale", null);
        Boundary boundary = (Boundary) ac.handleRequest("MostraSchermataPatologia", parameter);
        assertTrue(boundary instanceof SchermataPatologia);
    }
}
