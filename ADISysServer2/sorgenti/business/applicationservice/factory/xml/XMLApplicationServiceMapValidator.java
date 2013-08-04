package business.applicationservice.factory.xml;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
class XMLApplicationServiceMapValidator {
	private static final SchemaFactory factory = 
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	
	private static final String SRC_PATH = "sorgenti/business/applicationservice/factory/xml/";
	private static final String SCHEMA_FILE_NAME = "XMLApplicationServiceMapSchema.xsd";
	
	private static final String CANONICAL_SCHEMA_FILE_NAME = SRC_PATH + SCHEMA_FILE_NAME;
	
	private static Schema schema;
	
	static {
		try {
			schema = factory.newSchema(new StreamSource(CANONICAL_SCHEMA_FILE_NAME));
		} catch (SAXException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private static Validator validator = schema.newValidator();
	
	static void validate(String fileName) {
		try {
			validator.validate(new StreamSource(fileName));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
