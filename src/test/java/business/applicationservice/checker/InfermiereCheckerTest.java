package business.applicationservice.checker;
import business.applicationservice.exception.CommonException;
import business.applicationservice.exception.InvalidInfermiereFieldException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.fail;


public class InfermiereCheckerTest {

    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 30;
    private static final int MIN_COGNOME_VALUE = 3;
    private static final int MAX_COGNOME_VALUE = 30;

    InfermiereChecker infermiereChecker;
    List<Object> listOfValues;


    @Before
    public void setUp() throws Exception {
        infermiereChecker = new InfermiereChecker();
        listOfValues = new ArrayList<>();

    }

    @After
    public void tearDown() throws Exception {
        listOfValues = null;
    }


    @Test
    public void testCheckValideNameAndSurname() throws Exception {

        listOfValues.add("Giorgio");
        listOfValues.add("Volpini");

        infermiereChecker.check(listOfValues);
        assert (true); // significa che il check e' andato a buon fine e nessuna eccezione e' stata lanciata
    }


    @Test
    public void testCheckInvalidName() throws Exception {

        try {
            listOfValues.add("");
            infermiereChecker.check(listOfValues);
            fail("Exception was not thrown");
        } catch (CommonException e) {
            assertThat(e.getMessage(), equalTo("invalidNurseName"));
        }


    }

    @Test
    public void testCheckInvalidSurname() throws Exception {

        try {
            listOfValues.add("Ugo");
            listOfValues.add("");
            infermiereChecker.check(listOfValues);
            fail("Exception was not thrown");
        } catch (CommonException e) {
            assertThat(e.getMessage(), equalTo("invalidNurseSurname"));
        }

    }

    @Test(expected = InvalidInfermiereFieldException.class)
    public void testCheckNameTooBig() throws Exception {
        listOfValues.add("Questo e' un nome troppo lungo per essere memorizzato");

        infermiereChecker.check(listOfValues);

    }

    @Test(expected = InvalidInfermiereFieldException.class)
    public void testCheckSurnameTooBig() throws Exception {
        listOfValues.add("Nome OK");
        listOfValues.add("Cognome troppo lungo per essere memorizzato");
        infermiereChecker.check(listOfValues);

    }


    @Test
    public void testCheckNameExactlyMaxValue() throws Exception {
        String name = StringGenerator.getAStringOfExactlyLength(MAX_NOME_VALUE);
        listOfValues.add(name);
        listOfValues.add("il cognome");
        infermiereChecker.check(listOfValues);
        assert (true);
    }

    @Test
    public void testCheckNameExactlyMinValue() throws Exception {
        String name = StringGenerator.getAStringOfExactlyLength(MIN_NOME_VALUE);
        listOfValues.add(name);
        listOfValues.add("il cognome");
        infermiereChecker.check(listOfValues);
        assert (true);
    }


    @Test
    public void testCheckNameAndSurnameExactlyMaxValue() throws Exception {
        String nome = StringGenerator.getAStringOfExactlyLength(MAX_NOME_VALUE);
        String cognome = StringGenerator.getAStringOfExactlyLength(MAX_COGNOME_VALUE);

        assertThat(nome.length(), equalTo(MAX_NOME_VALUE));
        assertThat(cognome.length(), equalTo(MAX_COGNOME_VALUE));

        listOfValues.add(nome);
        listOfValues.add(cognome);
        infermiereChecker.check(listOfValues);
        assert (true);

    }

    @Test
    public void testCheckNameAndSurnameExactlyMinValue() throws Exception {
        String nome = StringGenerator.getAStringOfExactlyLength(MIN_NOME_VALUE);
        String cognome = StringGenerator.getAStringOfExactlyLength(MIN_COGNOME_VALUE);

        assertThat(nome.length(), equalTo(MIN_NOME_VALUE));
        assertThat(cognome.length(), equalTo(MIN_COGNOME_VALUE));

        listOfValues.add(nome);
        listOfValues.add(cognome);
        infermiereChecker.check(listOfValues);
        assert (true);

    }


    @Test
    public void testCheckNameUnderBoundaryValue() throws Exception {
        String nome = StringGenerator.getAStringOfExactlyLength(MAX_NOME_VALUE - 1);
        assertThat(nome.length(), equalTo(MAX_NOME_VALUE - 1));

        listOfValues.add(nome);
        listOfValues.add("il cognome");
        infermiereChecker.check(listOfValues);
        assert (true);

    }

    @Test(expected = InvalidInfermiereFieldException.class)
    public void testCheckNameOverBoundaryMaxValue() throws Exception {
        String nome = StringGenerator.getAStringOfExactlyLength(MAX_NOME_VALUE + 1);
        assertThat(nome.length(), equalTo(MAX_NOME_VALUE + 1));

        listOfValues.add(nome);
        listOfValues.add("il cognome");
        infermiereChecker.check(listOfValues);

    }


    @Test(expected = InvalidInfermiereFieldException.class)
    public void testCheckSurnameOverBoundaryMaxValue() throws Exception {
        String cognome = StringGenerator.getAStringOfExactlyLength(MAX_COGNOME_VALUE + 1);
        assertThat(cognome.length(), equalTo(MAX_COGNOME_VALUE + 1));

        listOfValues.add("il nome");
        listOfValues.add(cognome);
        infermiereChecker.check(listOfValues);

    }


    @Test(expected = InvalidInfermiereFieldException.class)
    public void testCheckNameUnderBoundaryMinValue() throws Exception {
        String nome = StringGenerator.getAStringOfExactlyLength(MIN_NOME_VALUE - 1);
        assertThat(nome.length(), equalTo(MIN_NOME_VALUE - 1));

        listOfValues.add(nome);
        infermiereChecker.check(listOfValues);

    }

    @Test(expected = InvalidInfermiereFieldException.class)
    public void testCheckSurnameUnderBoundaryMinValue() throws Exception {
        String cognome = StringGenerator.getAStringOfExactlyLength(MIN_COGNOME_VALUE - 1);
        assertThat(cognome.length(), equalTo(MIN_COGNOME_VALUE - 1));

        listOfValues.add("il nome");
        listOfValues.add(cognome);
        infermiereChecker.check(listOfValues);

    }

    @Test
    public void testCheckNameOverBoundaryMinValue() throws Exception {
        String nome = StringGenerator.getAStringOfExactlyLength(MIN_NOME_VALUE + 1);
        assertThat(nome.length(), equalTo(MIN_NOME_VALUE + 1));

        listOfValues.add(nome);
        listOfValues.add("il cognome");
        infermiereChecker.check(listOfValues);

    }

    @Test
    public void testCheckSurnameOverBoundaryMinValue() throws Exception {
        String cognome = StringGenerator.getAStringOfExactlyLength(MIN_COGNOME_VALUE + 1);
        assertThat(cognome.length(), equalTo(MIN_COGNOME_VALUE + 1));

        listOfValues.add("il nome");
        listOfValues.add(cognome);
        infermiereChecker.check(listOfValues);

    }


}
