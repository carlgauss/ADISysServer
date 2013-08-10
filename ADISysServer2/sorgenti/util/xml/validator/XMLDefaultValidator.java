package util.xml.validator;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
class XMLDefaultValidator implements XMLValidator {
	private static final SchemaFactory factory = 
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	
	private Schema schema;
	
	public XMLDefaultValidator(String canonicalSchemaFileName) {
		try {
			schema = factory.newSchema(new StreamSource(canonicalSchemaFileName));
		} catch (SAXException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private Validator validator = schema.newValidator();
	
	@Override
	public void validate(String fileName) throws SAXException {
		try {
			validator.validate(new StreamSource(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
