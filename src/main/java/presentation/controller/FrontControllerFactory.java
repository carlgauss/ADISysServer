package presentation.controller;

public class FrontControllerFactory {

    private FrontControllerFactory() {

    }

    public static FrontController buildInstance() {
        return new ADISysFrontController();
    }
}
