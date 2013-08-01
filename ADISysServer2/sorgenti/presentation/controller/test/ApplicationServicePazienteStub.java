package presentation.controller.test;

import presentation.controller.ApplicationService;
import util.Parameter;
import business.controller.ApplicationServicePaziente;
import business.controller.CRUG;
import mockit.Mock;
import mockit.MockUp;

public class ApplicationServicePazienteStub extends MockUp<ApplicationServicePaziente> implements CRUG, ApplicationService {

	@Mock
	public void create(Parameter parameter) {
		System.out.println("OK");

	}

	@Mock
	public void update(Parameter parameter) {
		System.out.println("OK");

	}

	@Mock
	public Object read(Parameter parameter) {
		System.out.println("OK");
		return null;
	}

	@Mock
	public Object[] getAll(Parameter parameter) {
		System.out.println("OK");
		return null;
	}

}
