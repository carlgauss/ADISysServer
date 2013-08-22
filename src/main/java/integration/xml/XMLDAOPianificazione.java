package integration.xml;

import business.entity.Intervento;
import business.entity.Pianificazione;
import org.joda.time.LocalDateTime;
import org.xml.sax.SAXException;
import util.FolderManager;
import util.xml.marshaller.XMLMarshaller;
import util.xml.marshaller.XMLMarshallerFactory;
import util.xml.validator.XMLValidator;
import util.xml.validator.XMLValidatorFactory;

import java.io.File;
import java.util.List;

public class XMLDAOPianificazione implements DAOPianificazione {
    private static final String PIANIFICAZIONE_HEADER = "pianificazione";

    private static final String PIANIFICAZIONE_FOLDER = "pianificazione";
    private static final String PIANIFICAZIONE_CURR_DIRECTORY = PIANIFICAZIONE_FOLDER + "/";
    private static final String XML_EXTENSION = ".xml";

    private static final String SEPARATOR = " ";
    private static final char OLD_HOUR_SEPARATOR = ':';
    private static final char HOUR_SEPARATOR = '_';

    private static final File XSD_PIANIFICAZIONE_SCHEMA_FILE = new File("schema/XMLPianificazioneSchema.xsd");

    private LocalDateTime now = LocalDateTime.now();
    private String nowString = now.toString();

    public XMLDAOPianificazione() {
        nowString = nowString.replace(OLD_HOUR_SEPARATOR, HOUR_SEPARATOR);
    }

    @Override
    public void export(List<Intervento> listaInterventi) throws SAXException {
        FolderManager.insertFolderIfNotExists(PIANIFICAZIONE_FOLDER);

        String xmlFileName = "";
        xmlFileName += PIANIFICAZIONE_HEADER;
        xmlFileName += SEPARATOR;
        xmlFileName += nowString;
        xmlFileName += XML_EXTENSION;

        String canonicalXMLFileName = PIANIFICAZIONE_CURR_DIRECTORY + xmlFileName;

        XMLMarshaller marshaller = XMLMarshallerFactory.buildInstance(canonicalXMLFileName, Pianificazione.class);

        Pianificazione pianificazione = new Pianificazione();
        pianificazione.setIntervento(listaInterventi);

        marshaller.marshal(pianificazione);

        XMLValidator validator = XMLValidatorFactory.buildInstance(XSD_PIANIFICAZIONE_SCHEMA_FILE);
        validator.validate(new File(canonicalXMLFileName));
    }
}