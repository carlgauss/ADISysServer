package presentation.controller.test;

import mockit.Mock;
import presentation.boundary.Boundary;

public class SchermataPazientiStub implements Boundary {

	public SchermataPazientiStub() {
		System.out.println("OK Constructed SchermataPaziente");
	}

	@Mock
	public void showWindow() {
		System.out.println("OK Shown SchermataPaziente");

	}

}
