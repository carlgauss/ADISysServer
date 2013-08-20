package presentation.boundary;

import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 20/08/13
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class Launcher {
    public static void main(String[] args) {
        FrontController fc = FrontControllerFactory.buildInstance();
        fc.processRequest("MostraSchermataPrincipale", null);
    }
}
