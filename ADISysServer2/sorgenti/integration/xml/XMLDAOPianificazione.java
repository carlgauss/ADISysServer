package integration.xml;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.xml.sax.SAXException;

import util.FolderManager;
import util.xml.marshaller.XMLMarshaller;
import util.xml.marshaller.XMLMarshallerFactory;
import util.xml.validator.XMLValidator;
import util.xml.validator.XMLValidatorFactory;

import business.entity.*;

public class XMLDAOPianificazione {
	private static final String PACKAGE_PATH = "sorgenti/integration/xml/";
	private static final String XML_SCHEMA_FILE_NAME = "XMLPianificazioneSchema.xsd";
	private static final String CANONICAL_XML_SCHEMA_FILE_NAME = PACKAGE_PATH + XML_SCHEMA_FILE_NAME;
	
	private static final String PIANIFICAZIONE_HEADER = "pianificazione";
	
	private static final String PIANIFICAZIONE_FOLDER = "pianificazione";
	private static final String PIANIFICAZIONE_CURR_DIRECTORY = PIANIFICAZIONE_FOLDER + "/";
	private static final String XML_EXTENSION = ".xml";
	
	private static final String SEPARATOR = " ";
	private static final char OLD_HOUR_SEPARATOR = ':';
	private static final char HOUR_SEPARATOR = '_';
	
	private LocalDateTime now = LocalDateTime.now();
	private String nowString = now.toString();

	public XMLDAOPianificazione() {
		nowString = nowString.replace(OLD_HOUR_SEPARATOR, HOUR_SEPARATOR);
	}
	
	public void export (List<Intervento> listaInterventi) throws SAXException {
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
		
		XMLValidator validator = XMLValidatorFactory.buildInstance(CANONICAL_XML_SCHEMA_FILE_NAME);
		validator.validate(canonicalXMLFileName);
	}
	
	
}