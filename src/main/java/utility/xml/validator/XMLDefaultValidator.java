package utility.xml.validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

class XMLDefaultValidator implements XMLValidator {
    private static String schemaLanguage =  XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private static SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLanguage);

    private Schema schema;

    public XMLDefaultValidator(File canonicalSchemaFileName) {
        try {
            schema = schemaFactory.newSchema(new StreamSource(canonicalSchemaFileName));
        } catch (SAXException e) {
            // TODO: È il modo migliore, questo di seguito per gestire, un' eccezione?
            e.printStackTrace();
            System.exit(0);
        }

        validator = schema.newValidator();
    }

    private Validator validator;

    @Override
    public void validate(File fileName) throws SAXException {
        try {
            validator.validate(new StreamSource(fileName));
        } catch (IOException e) {
            // TODO: È il modo migliore, questo di seguito, per gestire un' eccezione?
            e.printStackTrace();
        }
    }
}