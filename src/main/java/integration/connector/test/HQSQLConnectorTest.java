package integration.connector.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HQSQLConnectorTest {

    @Before
    public void setUp() throws Exception {
        Class.forName("integration.connector.HQSQLConnector");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testExecuteReadQuery() {
        //fail("Not yet implemented");
    }

    @Test
    public void testExecuteUpdateQuery() {
        //fail("Not yet implemented");
    }

}
