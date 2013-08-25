package integration.xml.validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

class XMLDefaultValidator implements XMLValidator {
    private static String schemaLanguage = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private static SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLanguage);

    private Schema schema;

    public XMLDefaultValidator(File canonicalSchemaFileName) {
        try {
            schema = schemaFactory.newSchema(new StreamSource(canonicalSchemaFileName));
        } catch (SAXException e) {
            e.printStackTrace();
            System.exit(1);
        }

        validator = schema.newValidator();
    }

    private Validator validator;

    @Override
    public boolean validate(File fileName) {
        boolean validated = false;
        try {
            validator.validate(new StreamSource(fileName));
            validated = true;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SAXException e) {

        }
        return validated;
    }
}
