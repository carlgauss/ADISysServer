package util.xml.marshaller;

public class XMLMarshallerFactory {

	private XMLMarshallerFactory() {
		
	}

	public static XMLMarshaller buildInstance(String canonicalXMLFileName, Class<?> clazz) {
		return new XMLJAXBMarshaller(canonicalXMLFileName, clazz);
	}
}
