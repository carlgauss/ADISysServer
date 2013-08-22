package presentation.controller;

public class ApplicationControllerFactory {

    private ApplicationControllerFactory() {

    }

    public static ApplicationController getApplicationController() {
        return new ADISysAC();
    }
}
