package presentation.boundary.test;

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
	(new SchermataEsportazione(null, false)).setVisible(true);
    }

    @Test
    public void testDialogoVerifica() {
	(new SchermataVerifica(null, false)).setVisible(true);
    }
    
    @Test
    public void testEditorInfermieri() {
	(new SchermataInfermieri(null, false)).setVisible(true);
    }
    
    @Test
    public void testEditorInterventi() {
	(new SchermataInterventi(null, false)).setVisible(true);
    }
    
    @Test
    public void testEditorPazienti() {
	(new SchermataPazienti(null, false)).setVisible(true);
    }
    
    @Test
    public void testPianificatore() {
	(new SchermataPrincipale(null, false)).setVisible(true);
    }
}
