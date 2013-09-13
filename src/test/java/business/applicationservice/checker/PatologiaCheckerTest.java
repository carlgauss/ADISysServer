package business.applicationservice.checker;
import business.applicationservice.exception.InvalidPatologiaFieldException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
public class PatologiaCheckerTest {

    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 20;

    PatologiaChecker patologiaChecker;
    List<Object> listOfValues;

    @Before
    public void setUp() throws Exception {
        patologiaChecker = new PatologiaChecker();
        listOfValues = new ArrayList<>();
    }


    @After
    public void tearDown() throws Exception {
         patologiaChecker = null;
        listOfValues = null;
    }

    @Test
    public void testCheckAllValid() throws Exception {
        listOfValues.add("123456");
        listOfValues.add("nome patologia");
        listOfValues.add(3);
        patologiaChecker.check(listOfValues);
    }

    @Test (expected = InvalidPatologiaFieldException.class)
    public void testCheckInvalidNameEmpty() throws Exception {
        String nomePatologia  = StringGenerator.getAStringOfExactlyLength(0);
        listOfValues.add("123456");
        listOfValues.add(nomePatologia);

        patologiaChecker.check(listOfValues);

    }

    @Test
    public void testCheckNameExactMinValue() throws Exception {
        String nomePatologia  = StringGenerator.getAStringOfExactlyLength(MIN_NOME_VALUE);
        listOfValues.add("123456");
        listOfValues.add(nomePatologia);
        listOfValues.add(3);
        patologiaChecker.check(listOfValues);

    }

    @Test
    public void testCheckNameExactMaxValue() throws Exception {
        String nomePatologia  = StringGenerator.getAStringOfExactlyLength(MAX_NOME_VALUE);
        listOfValues.add("123456");
        listOfValues.add(nomePatologia);
        listOfValues.add(3);
        patologiaChecker.check(listOfValues);

    }





}
