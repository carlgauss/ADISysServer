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


    DAOPianificazione pianificazione;

    @Before
    public void setUp() throws Exception {
        Infermiere infermiere = new Infermiere();
        infermiere.setId("12");

        pianificazione = new XMLDAOPianificazione(infermiere);

        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);

        DAOInterventoTest.fillAll();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testExport() {
        try {
            pianificazione.export(DAOInterventoTest.interventi);
            assertTrue(true);
        } catch (SAXException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

}
