package util.xml.validator;

import org.xml.sax.SAXException;

public interface XMLValidator {

	public abstract void validate(String fileName) throws SAXException;

}