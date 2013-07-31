package presentation.boundary.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import presentation.boundary.*;

public class BoundaryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDialogoEsportazione() {
	(new DialogoEsportazione(null, false)).setVisible(true);
    }

    @Test
    public void testDialogoVerifica() {
	(new DialogoVerifica(null, false)).setVisible(true);
    }
    
    @Test
    public void testEditorInfermieri() {
	(new EditorInfermieri(null, false)).setVisible(true);
    }
    
    @Test
    public void testEditorInterventi() {
	(new EditorInterventi(null, false)).setVisible(true);
    }
    
    @Test
    public void testEditorPazienti() {
	(new EditorPazienti(null, false)).setVisible(true);
    }
    
    @Test
    public void testPianificatore() {
	//TODO resolve opening problem
	(new Pianificatore(null, false)).setVisible(true);
	fail("Can't open correctly");
    }
}
