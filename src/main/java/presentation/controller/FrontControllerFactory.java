package presentation.controller;

public class FrontControllerFactory {

    private FrontControllerFactory() {

    }

    public static FrontController getFrontController() {
        return new ADISysFC();
    }
}
