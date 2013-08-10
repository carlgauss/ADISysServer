package integration.xml.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import integration.connector.HQSQLConnector;
import integration.dao.DAOFactory;
import integration.dao.DAO;
import business.entity.*;
import integration.dao.test.DAOInterventoTest;
import integration.dao.test.HQSQLConnectorStub;
import mockit.Mockit;

public class JAXBExample {
	public static void main(String... args) {
		Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);
		DAOInterventoTest.fillAll();

		DAO<Intervento> dao = DAOFactory.buildInstance("DAOIntervento");
		Pianificazione pianificazione = new Pianificazione();
		pianificazione.setIntervento(dao.getAll());

		try {

			File file = new File("testfiles\filePianif.xml");
			JAXBContext jaxbContext = JAXBContext
					.newInstance(Pianificazione.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(pianificazione, file);
			jaxbMarshaller.marshal(pianificazione, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
