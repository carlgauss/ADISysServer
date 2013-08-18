package presentation.controller.test;

import java.util.List;

import presentation.controller.ApplicationService;
import util.Parameter;
import business.applicationservice.ApplicationServicePaziente;
import business.applicationservice.CRUG;
import business.entity.Paziente;
import mockit.Mock;
import mockit.MockUp;

public class ApplicationServicePazienteStub implements CRUG<Paziente>, ApplicationService {

	@Mock
	public void create(Parameter parameter) {
		System.out.println("OK Inserisci paziente");

	}

	@Mock
	public void update(Parameter parameter) {
		System.out.println("OK Modifica paziente");

	}

	@Mock
	public Paziente read(Parameter parameter) {
		System.out.println("OK");
		return null;
	}

	@Mock
	public List<Paziente> getAll(Parameter parameter) {
		System.out.println("OK");
		return null;
	}

}
