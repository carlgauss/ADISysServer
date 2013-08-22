package util.xml.validator;

import java.io.File;

public class XMLValidatorFactory {

    public static XMLValidator buildInstance(File canonicalFileName) {
        return new XMLDefaultValidator(canonicalFileName);
    }

    private XMLValidatorFactory() {

    }

}
