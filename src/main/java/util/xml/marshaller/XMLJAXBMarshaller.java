package util.xml.marshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

class XMLJAXBMarshaller implements XMLMarshaller {

    private File file;
    private Marshaller jaxbMarshaller;
    private Unmarshaller jaxbUnmarshaller;

    public XMLJAXBMarshaller(String canonicalXMLFileName, Class<?> clazz) {
        try {

            file = new File(canonicalXMLFileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void marshal(Object entity) {
        try {
            jaxbMarshaller.marshal(entity, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object unmarshal() {
        Object result = null;
        try {
            result = jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

}
