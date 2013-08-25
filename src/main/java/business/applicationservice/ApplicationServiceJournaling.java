package business.applicationservice;

import business.applicationservice.exception.InvalidJournalingFileException;
import business.entity.Journaling;
import business.transfer.JournalingFile;
import integration.xml.marshaller.XMLMarshaller;
import integration.xml.marshaller.XMLMarshallerFactory;
import integration.xml.validator.XMLValidator;
import integration.xml.validator.XMLValidatorFactory;
import presentation.controller.ApplicationService;
import utility.FolderManager;
import utility.Parameter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO
public class ApplicationServiceJournaling implements ApplicationService {

    private static final String JOURNALING_PATH = "journaling";
    private static final File JOURNALING_FILE_SCHEMA = new File("schema/XMLJournalingSchema.xsd");
    private static final String SEPARATOR = "/";

    private static final String DATE_REGEX = "(([0-9])+[-]){2}([0-9])+";
    private static final String XML_EXTENSION = "xml";
    private static final String FILE_SYNTAX_REGEX = "journaling[ ]" + "([a-zA-Z ])+[ ]" + DATE_REGEX + "[.]" + XML_EXTENSION;

    private FileFilter filter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            String fileName = pathname.getName();

            return fileName.matches(FILE_SYNTAX_REGEX);
        }
    };

    public Journaling importFile(Parameter parameter) throws InvalidJournalingFileException {
        Journaling journaling = null;

        String journalingFileName = (String) parameter.getValue("journaling");

        String canonicalJournalingFileName = JOURNALING_PATH + SEPARATOR + journalingFileName;
        File journalingFile = new File(canonicalJournalingFileName);
        XMLValidator validator = XMLValidatorFactory.getValidator(JOURNALING_FILE_SCHEMA);

        if (validator.validate(journalingFile)) {
            XMLMarshaller unmarshaller = null;
            unmarshaller = XMLMarshallerFactory.getMarshaller(canonicalJournalingFileName, Journaling.class);

            journaling = (Journaling) unmarshaller.unmarshal();
        } else {
            throw new InvalidJournalingFileException("invalidJournaling");
        }

        return journaling;
    }

    public List<JournalingFile> getFileList(Parameter parameter) {
        List<JournalingFile> fileListDetailed = null;

        try {
            FolderManager.createFolderIfNotExists(JOURNALING_PATH);

            File folder = new File(JOURNALING_PATH);

            fileListDetailed = new ArrayList<>();
            File[] fileList = folder.listFiles(filter);

            for (File file : fileList) {
                String fileName = file.getName();
                JournalingFile decoratedFileName = new JournalingFile(fileName);

                fileListDetailed.add(decoratedFileName);
            }

            Collections.sort(fileListDetailed);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return fileListDetailed;
    }


}
