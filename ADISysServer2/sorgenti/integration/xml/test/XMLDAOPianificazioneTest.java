package integration.xml.test;

import static org.junit.Assert.*;
import integration.connector.HQSQLConnector;
import integration.dao.test.DAOInterventoTest;
import integration.dao.test.HQSQLConnectorStub;
import integration.xml.XMLDAOPianificazione;

import mockit.Mockit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class XMLDAOPianificazioneTest {

	XMLDAOPianificazione pianif = new XMLDAOPianificazione();
	
	@Before
	public void setUp() throws Exception {
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
