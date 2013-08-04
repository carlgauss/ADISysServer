package business.applicationservice.factory.xml.test;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XMLFromXSDTest {
	
	private static final String SRC_PATH = "sorgenti/business/applicationservice/factory/xml/";
	
	public static void main (String...strings) throws SAXException, IOException {
        SchemaFactory factory = 
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(SRC_PATH + "XMLApplicationServiceMapSchema.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(SRC_PATH + "XMLApplicationServiceMap.xml"));
           
	}
}
