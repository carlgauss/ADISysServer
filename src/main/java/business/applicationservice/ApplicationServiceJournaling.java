package business.applicationservice;

import business.applicationservice.exception.InvalidJournalingFileException;
import business.entity.Journaling;
import integration.xml.marshaller.XMLMarshaller;
import integration.xml.marshaller.XMLMarshallerFactory;
import integration.xml.validator.XMLValidator;
import integration.xml.validator.XMLValidatorFactory;
import presentation.controller.ApplicationService;
import utility.Parameter;

import java.io.File;

//TODO
public class ApplicationServiceJournaling implements ApplicationService {

    private static final String JOURNALING_PATH = "journaling/";
    private static final File JOURNALING_FILE_SCHEMA = new File("schema/XMLJournalingSchema.xsd");

    public Journaling importFile(Parameter parameter) throws InvalidJournalingFileException {
        Journaling journaling = null;

        String journalingFileName = (String) parameter.getValue("journaling");

        File journalingFile = new File(JOURNALING_PATH + journalingFileName);
        XMLValidator validator = XMLValidatorFactory.getValidator(JOURNALING_FILE_SCHEMA);

        if (validator.validate(journalingFile)) {
            XMLMarshaller unmarshaller = XMLMarshallerFactory.getMarshaller(journalingFileName, Journaling.class);

            journaling = (Journaling) unmarshaller.unmarshal();
        } else {
            throw new InvalidJournalingFileException("invalidJournaling");
        }

        return journaling;
    }

}
