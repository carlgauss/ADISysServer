package integration.xml;

import business.entity.Intervento;
import business.entity.Pianificazione;
import integration.connector.HQSQLConnector;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import integration.dao.DAOInterventoTest;
import integration.dao.HQSQLConnectorStub;
import mockit.Mockit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class JAXBExample {
    public static void main(String... args) {
        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);
        DAOInterventoTest.fillAll();

        DAO<Intervento> dao = DAOFactory.getDAOEntity("DAOIntervento");
        Pianificazione pianificazione = new Pianificazione();
        pianificazione.setIntervento(dao.getAll());

        try {

            File file = new File("testfiles/filePianif.xml");
            JAXBContext jaxbContext = JAXBContext
                    .newInstance(Pianificazione.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(pianificazione, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
