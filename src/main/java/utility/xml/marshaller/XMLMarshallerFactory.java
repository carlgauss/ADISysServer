package utility.xml.marshaller;

public class XMLMarshallerFactory {

    private XMLMarshallerFactory() {

    }

    public static XMLMarshaller getMarshaller(String canonicalXMLFileName, Class<?> clazz) {
        return new XMLJAXBMarshaller(canonicalXMLFileName, clazz);
    }
}
