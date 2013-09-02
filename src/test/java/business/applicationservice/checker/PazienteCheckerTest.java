package business.applicationservice.checker;
import business.applicationservice.exception.InvalidPazienteFieldException;
import business.entity.Patologia;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
public class PazienteCheckerTest {

    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 30;
    private static final int MIN_COGNOME_VALUE = 3;
    private static final int MAX_COGNOME_VALUE = 30;

    List<Object> listOfValues;
    PazienteChecker pazienteChecker;

    Patologia patologia;

    @Before
    public void setUp() throws Exception {
        listOfValues = new ArrayList<>();
        pazienteChecker = new PazienteChecker();
        patologia = new Patologia();
    }

    @After
    public void tearDown() throws Exception {
        listOfValues = null;
        pazienteChecker = null;
    }

    @Test
    public void testCheckAllOk() throws Exception {
        listOfValues.add("nome");
        listOfValues.add("cognome");
        listOfValues.add("12/12/1900");
        listOfValues.add("889");

        patologia.setCodice("123456");
        patologia.setNome("Nome patologia");
        patologia.setGravita(4);
        List<Patologia> patologiaList = new ArrayList<>();
        patologiaList.add(patologia);
        listOfValues.add(patologiaList);

        pazienteChecker.check(listOfValues);

    }


    @Test (expected = InvalidPazienteFieldException.class)
    public void testCheckInvalidName() throws Exception {
        String name = getAStringOfExactlyLength(0);
        assertThat(name.length(), equalTo(0));
        listOfValues.add(name);
        pazienteChecker.check(listOfValues);

    }

    @Test (expected = InvalidPazienteFieldException.class)
    public void testCheckInvalidSurname() throws Exception {
        String nome = getAStringOfExactlyLength(5);
        String cognome = getAStringOfExactlyLength(0);

        listOfValues.add(nome);
        listOfValues.add(cognome);
        pazienteChecker.check(listOfValues);

    }

    @Test (expected = InvalidPazienteFieldException.class)
    public void testCheckInvalidData() throws Exception {
        String nome = getAStringOfExactlyLength(5);
        String cognome = getAStringOfExactlyLength((MAX_COGNOME_VALUE + MIN_COGNOME_VALUE) /2);
        String data = "00/12/1200";

        listOfValues.add(nome);
        listOfValues.add(cognome);
        listOfValues.add(data);
        pazienteChecker.check(listOfValues);
    }

    @Test (expected = InvalidPazienteFieldException.class)
    public void testCheckInvalidData2() throws Exception {
        String nome = getAStringOfExactlyLength(5);
        String cognome = getAStringOfExactlyLength((MAX_COGNOME_VALUE + MIN_COGNOME_VALUE) /2);
        String data = "01/00/1200";

        listOfValues.add(nome);
        listOfValues.add(cognome);
        listOfValues.add(data);
        pazienteChecker.check(listOfValues);
    }

    @Test (expected = InvalidPazienteFieldException.class)
    public void testCheckInvalidData3() throws Exception {
        String nome = getAStringOfExactlyLength(5);
        String cognome = getAStringOfExactlyLength((MAX_COGNOME_VALUE + MIN_COGNOME_VALUE) /2);
        String data = "1/1/1000000000";

        listOfValues.add(nome);
        listOfValues.add(cognome);
        listOfValues.add(data);
        listOfValues.add("numero");


        patologia.setCodice("123456");
        patologia.setNome("Nome patologia");
        patologia.setGravita(4);
        List<Patologia> patologiaList = new ArrayList<>();
        patologiaList.add(patologia);
        listOfValues.add(patologiaList);
        pazienteChecker.check(listOfValues);
    }



    @Test (expected = InvalidPazienteFieldException.class)
    public void testCheckInvalidPatologiaList() throws Exception {
        String nome = getAStringOfExactlyLength(5);
        String cognome = getAStringOfExactlyLength((MAX_COGNOME_VALUE + MIN_COGNOME_VALUE) /2);
        String data = "1/1/1000000000";

        listOfValues.add(nome);
        listOfValues.add(cognome);
        listOfValues.add(data);
        listOfValues.add("numero");


        patologia.setCodice("123456");
        patologia.setNome("Nome patologia");
        patologia.setGravita(4);
        List<Patologia> patologiaList = new ArrayList<>();
       // patologiaList.add(patologia);
        listOfValues.add(patologiaList);
        pazienteChecker.check(listOfValues);
    }


    @Test
    public void testCheckNameExactMaxValue() throws Exception {
        String name = getAStringOfExactlyLength(MAX_NOME_VALUE);
        listOfValues.add(name);
        listOfValues.add("il cognome");
        listOfValues.add("5/6/1900");
        listOfValues.add("numero di telefono");

        patologia.setCodice("123456");
        patologia.setNome("Nome patologia");
        patologia.setGravita(4);
        List<Patologia> patologiaList = new ArrayList<>();
        patologiaList.add(patologia);
        listOfValues.add(patologiaList);
        pazienteChecker.check(listOfValues);

    }

    @Test
    public void testCheckNameExactMinValue() throws Exception {
        String name = getAStringOfExactlyLength(MIN_NOME_VALUE);
        listOfValues.add(name);
        listOfValues.add("il cognome");
        listOfValues.add("9/9/2009");
        listOfValues.add("numero di telefono");

        patologia.setCodice("123456");
        patologia.setNome("Nome patologia");
        patologia.setGravita(5);
        List<Patologia> patologiaList = new ArrayList<>();
        patologiaList.add(patologia);
        listOfValues.add(patologiaList);
        pazienteChecker.check(listOfValues);

    }

    @Test
    public void testCheckSurnameExactMaxValue() throws Exception {
        String cognome = getAStringOfExactlyLength(MAX_COGNOME_VALUE);

        listOfValues.add("Il nome");
        listOfValues.add(cognome);
        listOfValues.add("5/6/1900");
        listOfValues.add("numero di telefono");

        patologia.setCodice("123456");
        patologia.setNome("Nome patologia");
        patologia.setGravita(4);
        List<Patologia> patologiaList = new ArrayList<>();
        patologiaList.add(patologia);
        listOfValues.add(patologiaList);
        pazienteChecker.check(listOfValues);

    }

    @Test
    public void testCheckSurnameExactMinValue() throws Exception {
        String cognome = getAStringOfExactlyLength(MIN_COGNOME_VALUE);

        listOfValues.add("Il nome");
        listOfValues.add(cognome);
        listOfValues.add("7/7/1907");
        listOfValues.add("numero di telefono");

        patologia.setCodice("123456");
        patologia.setNome("Nome patologia");
        patologia.setGravita(4);
        List<Patologia> patologiaList = new ArrayList<>();
        patologiaList.add(patologia);
        listOfValues.add(patologiaList);
        pazienteChecker.check(listOfValues);

    }

    private static String getAStringOfExactlyLength(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += "v";
        }
        return result;
    }
}
