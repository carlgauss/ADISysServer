package business.applicationservice;
import business.entity.Patologia;
import business.entity.Paziente;
import business.transfer.Parameter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
public class ApplicationServicePazienteTest {

    ApplicationServicePaziente asPaziente;
    Paziente paziente;
    Patologia patologia;
    Parameter parameter;

    @Before
    public void setUp() throws Exception {
        asPaziente = new ApplicationServicePaziente();
        paziente = new Paziente();
        patologia = new Patologia();
        parameter = new Parameter();
    }

    @After
    public void tearDown() throws Exception {
        asPaziente = null;
        paziente = null;
        patologia = null;
        parameter = null;
    }

    @Test
    public void testCreate() throws Exception {
        parameter.setValue("id", "999");
        parameter.setValue("nome", "nome Paziente");
        parameter.setValue("cognome", "cognome paziente");
        parameter.setValue("data", "19/01/1980");

        List<String> numeroList = new ArrayList<>();
        numeroList.add("3334455666");
        parameter.setValue("numero", numeroList);

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("777777");
        patologia.setNome("Cough");
        patologia.setGravita(2);
        patologiaList.add(patologia);


        parameter.setValue("patologia", patologiaList);

        asPaziente.create(parameter);
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testRead() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }
}
