package presentation.controller;

public class ApplicationControllerFactory {

	private ApplicationControllerFactory() {

	}

	public static ApplicationController buildIstance() {
		return new ADISysApplicationController();
	}
}
