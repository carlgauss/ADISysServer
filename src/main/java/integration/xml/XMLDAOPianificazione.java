package integration.xml;

import business.entity.Infermiere;
import business.entity.Intervento;
import business.entity.Pianificazione;
import org.joda.time.LocalDate;
import org.xml.sax.SAXException;
import utility.FolderManager;
import utility.xml.marshaller.XMLMarshaller;
import utility.xml.marshaller.XMLMarshallerFactory;
import utility.xml.validator.XMLValidator;
import utility.xml.validator.XMLValidatorFactory;

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

    private LocalDate now = LocalDate.now();
    private String nowString = now.toString();

    public XMLDAOPianificazione(Infermiere infermiere) {
        this.infermiere = infermiere;
    }

    private Infermiere infermiere;

    @Override
    public void export(List<Intervento> listaInterventi) throws SAXException {
        FolderManager.createFolderIfNotExists(PIANIFICAZIONE_FOLDER);

        String xmlFileName = "";
        xmlFileName += PIANIFICAZIONE_HEADER;
        xmlFileName += SEPARATOR;
        xmlFileName += infermiere.toString();
        xmlFileName += SEPARATOR;
        xmlFileName += nowString;
        xmlFileName += XML_EXTENSION;

        String canonicalXMLFileName = PIANIFICAZIONE_CURR_DIRECTORY + xmlFileName;

        XMLMarshaller marshaller = XMLMarshallerFactory.getMarshaller(canonicalXMLFileName, Pianificazione.class);

        Pianificazione pianificazione = new Pianificazione();
        pianificazione.setIntervento(listaInterventi);

        marshaller.marshal(pianificazione);

        XMLValidator validator = XMLValidatorFactory.getValidator(XSD_PIANIFICAZIONE_SCHEMA_FILE);
        validator.validate(new File(canonicalXMLFileName));
    }
}