package presentation.controller;

public class FrontControllerFactory {

	private FrontControllerFactory() {

	}

	public static FrontController buildIstance() {
		return new ADISysFrontController();
	}
}
