package business.applicationservice.factory.xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import business.applicationservice.factory.ApplicationServiceMap;

public class XMLApplicationServiceMapper {
	
	private static final String SRC_PATH = "sorgenti/business/applicationservice/factory/xml/";
	private static final String XML_FILE_NAME = "XMLApplicationServiceMap.xml";
	
	private static final String CANONICAL_XML_FILE_NAME = SRC_PATH + XML_FILE_NAME;
	
    private static final SAXBuilder builder = new SAXBuilder();
    
	private static final String APPLICATION_SERVICE_NAME = "serviceName";
	private static final String APPLICATION_SERVICE_CLASS = "class";
	private static final String APPLICATION_SERVICE_METHOD = "method";

	public static void map(ApplicationServiceMap map) {
		Document document = buildDocument();
		XMLApplicationServiceMapValidator.validate(CANONICAL_XML_FILE_NAME);
		
		Element root = document.getRootElement();
		List<Element> elements = root.getChildren();
		
		for(Element e : elements) {
			String applicationServiceName = e.getChildText(APPLICATION_SERVICE_NAME);
			String applicationServiceClass = e.getChildText(APPLICATION_SERVICE_CLASS);
			String applicationServiceMethod = e.getChildText(APPLICATION_SERVICE_METHOD);
			
			map.selectApplicationServiceBy(applicationServiceName);
			map.setApplicationServiceValues(applicationServiceClass, applicationServiceMethod);
		}
	}

	private static Document buildDocument() {
		Document document = null;
    	try {
			document = builder.build(new File(CANONICAL_XML_FILE_NAME));
		} catch (JDOMException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
    	
    	return document;
	}
	
}
