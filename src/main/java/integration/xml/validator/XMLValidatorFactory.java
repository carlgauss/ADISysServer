package integration.xml.validator;

import java.io.File;

public class XMLValidatorFactory {

    public static XMLValidator getValidator(File canonicalFileName) {
        return new XMLDefaultValidator(canonicalFileName);
    }

    private XMLValidatorFactory() {

    }

}
