package integration.xml;

import business.entity.Infermiere;
import integration.connector.HQSQLConnector;
import integration.dao.DAOInterventoTest;
import integration.dao.HQSQLConnectorStub;
import mockit.Mockit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import static org.junit.Assert.assertTrue;

public class XMLDAOPianificazioneTest {


    DAOPianificazione pianif;

    @Before
    public void setUp() throws Exception {
        Infermiere inf = new Infermiere();
        inf.setId("12");

        pianif = new XMLDAOPianificazione(inf);

        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);

        DAOInterventoTest.fillAll();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testExport() {
        try {
            pianif.export(DAOInterventoTest.interventi);
            assertTrue(true);
        } catch (SAXException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

}
