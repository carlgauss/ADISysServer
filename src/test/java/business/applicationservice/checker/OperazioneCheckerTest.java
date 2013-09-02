package business.applicationservice.checker;
import business.applicationservice.exception.InvalidOperazioneFieldException;
import business.entity.Patologia;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
public class OperazioneCheckerTest {
    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 30;

    OperazioneChecker operazioneChecker;
    List<Object> listOfValues;
    Patologia patologia;

    @Before
    public void setUp() throws Exception {
        operazioneChecker = new OperazioneChecker();
        listOfValues = new ArrayList<>();
        patologia = new Patologia();

    }

    @After
    public void tearDown() throws Exception {
        operazioneChecker = null;
        listOfValues = null;
        patologia = null;

    }

    @Test
    public void testCheckAllValidFields() throws Exception {
        listOfValues.add("nome operazione");

        patologia.setCodice("123456");
        patologia.setNome("Nome patologia");
        patologia.setGravita(4);
        List<Patologia> patologiaList = new ArrayList<>();
        patologiaList.add(patologia);
        listOfValues.add(patologiaList);


    }

    @Test (expected = InvalidOperazioneFieldException.class)
    public void testCheckNameEmpty() throws Exception {
        String name = StringGenerator.getAStringOfExactlyLength(0);
        listOfValues.add(name);
        operazioneChecker.check(listOfValues);

    }

    @Test
    public void testCheckNameExactMinValue() throws Exception {
        String name = StringGenerator.getAStringOfExactlyLength(MIN_NOME_VALUE);
        listOfValues.add(name);

        patologia.setCodice("123456");
        patologia.setNome("Nome patologia");
        patologia.setGravita(4);
        List<Patologia> patologiaList = new ArrayList<>();
        patologiaList.add(patologia);
        listOfValues.add(patologiaList);

        operazioneChecker.check(listOfValues);

    }


    @Test (expected = InvalidOperazioneFieldException.class)
    public void testCheckNameExactOneUnderMinValue() throws Exception {
        String name = StringGenerator.getAStringOfExactlyLength(MIN_NOME_VALUE - 1);
        listOfValues.add(name);

        operazioneChecker.check(listOfValues);

    }

    @Test
    public void testCheckNameExactOneOverMinValue() throws Exception {
        String name = StringGenerator.getAStringOfExactlyLength(MIN_NOME_VALUE + 1);
        listOfValues.add(name);

        patologia.setCodice("123456");
        patologia.setNome("Nome patologia");
        patologia.setGravita(4);
        List<Patologia> patologiaList = new ArrayList<>();
        patologiaList.add(patologia);
        listOfValues.add(patologiaList);

        operazioneChecker.check(listOfValues);

    }

    @Test
    public void testCheckNameExactMaxValue() throws Exception {
        String name = StringGenerator.getAStringOfExactlyLength(MAX_NOME_VALUE);
        listOfValues.add(name);

        patologia.setCodice("123456");
        patologia.setNome("Nome patologia");
        patologia.setGravita(4);
        List<Patologia> patologiaList = new ArrayList<>();
        patologiaList.add(patologia);
        listOfValues.add(patologiaList);

        operazioneChecker.check(listOfValues);

    }


    @Test
    public void testCheckNameExactOneUnderMaxValue() throws Exception {
        String name = StringGenerator.getAStringOfExactlyLength(MAX_NOME_VALUE - 1);
        listOfValues.add(name);

        patologia.setCodice("123456");
        patologia.setNome("Nome patologia");
        patologia.setGravita(4);
        List<Patologia> patologiaList = new ArrayList<>();
        patologiaList.add(patologia);
        listOfValues.add(patologiaList);

        operazioneChecker.check(listOfValues);

    }

    @Test  (expected = InvalidOperazioneFieldException.class)
    public void testCheckNameExactOneOverMaxValue() throws Exception {
        String name = StringGenerator.getAStringOfExactlyLength(MAX_NOME_VALUE + 1);
        listOfValues.add(name);

        operazioneChecker.check(listOfValues);

    }

    @Test  (expected = InvalidOperazioneFieldException.class)
    public void testCheckInvalidPatologiaList() throws Exception {
        listOfValues.add("nome patologia");
        List<Patologia> patologiaList = new ArrayList<>();

        listOfValues.add(patologiaList);

        operazioneChecker.check(listOfValues);

    }
}
