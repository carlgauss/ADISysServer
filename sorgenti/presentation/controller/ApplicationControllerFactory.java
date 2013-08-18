package presentation.controller;

public class ApplicationControllerFactory {

	private ApplicationControllerFactory() {

	}

	public static ApplicationController buildInstance() {
		return new ADISysApplicationController();
	}
}
