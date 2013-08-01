package presentation.controller;

import util.Parameter;

class ADISysFrontController implements FrontController {

	public Object processRequest(String request, Parameter parameter) {
		ApplicationController ac = ApplicationControllerFactory.buildIstance();
		return ac.handleRequest(request, parameter);
	}

}
