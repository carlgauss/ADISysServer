package presentation.boundary;

import integration.connector.HQSQLConnector;
import integration.dao.test.DAOInterventoTest;
import integration.dao.test.HQSQLConnectorStub;
import mockit.Mockit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 19/08/13
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class SchermataPrincipaleTest {
    @Before
    public void setUp() throws Exception {
        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);
        DAOInterventoTest.fillAll();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testShowWindow() throws Exception {
        SchermataPrincipale pr = new SchermataPrincipale();
        pr.showWindow(null);
    }
}
