package util.xml.validator;

import org.xml.sax.SAXException;

import java.io.File;

public interface XMLValidator {

    public abstract void validate(File fileName) throws SAXException;

}