package business.applicationservice.exception;

import util.MessageDisplayer;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 22/08/13
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class InvalidInfermiereFieldException extends CommonException {
    public InvalidInfermiereFieldException(String invalidName) {
        this.invalidName = invalidName;
    }

    String invalidName;

    @Override
    public void reportException() {
        MessageDisplayer.showErrorMessage(null, invalidName);
    }
}
