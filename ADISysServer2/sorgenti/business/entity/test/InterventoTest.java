package business.entity.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.entity.Intervento;
import business.entity.Operazione;

public class InterventoTest {
	
	private static final int FIRST = 0;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetOperazione() {
		//This test verifies if the composition relation is correct
		Intervento intrv = new Intervento();
		List<Operazione> list = new LinkedList<>();
		Operazione op = new Operazione();
		list.add(op);
		intrv.setOperazione(list);
		List<Operazione> anotherList = intrv.getOperazione();
		Operazione anotherOp = anotherList.get(FIRST);
		assertTrue("Not a valid composition relation", op != anotherOp);
		}

}
