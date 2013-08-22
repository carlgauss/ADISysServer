package presentation.boundary;

import integration.connector.HQSQLConnector;
import integration.dao.HQSQLConnectorStub;
import mockit.Mockit;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 22/08/13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class SchermataPrincipaleTest {
    @Before
    public void setUp() throws Exception {
        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);
    }

    @Test
    public void testShowWindow() throws Exception {
        SchermataPrincipale sc = new SchermataPrincipale();
        sc.showWindow(null);
        assertTrue(true);
    }
}
