package util.xml.validator;

public class XMLValidatorFactory {
	
	public static XMLValidator buildInstance(String canonicalFileName) {
		return new XMLDefaultValidator(canonicalFileName);
	}

	private XMLValidatorFactory() {

	}

}
