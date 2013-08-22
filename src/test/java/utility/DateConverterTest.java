package utility;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class DateConverterTest {
    LocalDate dateToConvert;
    String stringDateConverted;
    String stringDateExpected;

    String stringToConvert;
    LocalDate dateExpected;
    LocalDate dateConverted;

    @Before
    public void setUp() throws Exception{
    }

    @After
    public void tearDown() throws Exception {
        dateToConvert = null;
        stringDateConverted = null;
        stringDateExpected = null;
        stringToConvert = null;
    }

    @Test
    public void testConvertFromDateToString() throws Exception {
        dateToConvert = new LocalDate(2012, 12, 21);
        stringDateExpected =  "2012-12-21";
        stringDateConverted = DateConverter.convertFromDateToString(dateToConvert);
        assertThat(stringDateConverted, equalTo(stringDateExpected));
    }

    @Test
    public void testFirstGenYearZero() throws Exception{
        dateToConvert = new LocalDate(0, 1, 1);
        stringDateExpected = "0000-01-01";

        stringDateConverted = DateConverter.convertFromDateToString(dateToConvert);
        assertThat(stringDateConverted, equalTo(stringDateExpected));
    }

    @Test
    public void testNegativeYear() throws Exception{
        dateToConvert = new LocalDate(-33, 1, 1);
        stringDateExpected = "-0033-01-01";

        stringDateConverted = DateConverter.convertFromDateToString(dateToConvert);
        assertThat(stringDateConverted, equalTo(stringDateExpected));
    }


    @Test
    public void testNegativeBound() throws Exception{
        dateToConvert = new LocalDate(-9999, 1, 1);
        stringDateExpected = "-9999-01-01";

        stringDateConverted = DateConverter.convertFromDateToString(dateToConvert);
        assertThat(stringDateConverted, equalTo(stringDateExpected));
    }

    @Test
    public void testUpperBound() throws Exception{
        dateToConvert = new LocalDate(9999, 12, 31);
        stringDateExpected = "9999-12-31";

        stringDateConverted = DateConverter.convertFromDateToString(dateToConvert);
        assertThat(stringDateConverted, equalTo(stringDateExpected));
    }


    //From String to Date Tests
    @Test
    public void testConvertFromStringToDate() throws Exception {
        stringToConvert = "2000-10-10";
        dateExpected = new LocalDate(2000, 10, 10);

        dateConverted = DateConverter.convertFromStringToDate(stringToConvert);
        assertThat(dateConverted, equalTo(dateExpected));
    }

    @Test  (expected = NumberFormatException.class)
    public void testConvertStringNotWellFormatted() throws Exception {
        stringToConvert = "2000--10-10";
        dateExpected = new LocalDate(2000, 10, 10);

        dateConverted = DateConverter.convertFromStringToDate(stringToConvert);
    }


    @Test  (expected = NumberFormatException.class)
    public void testConvertEmptyString() throws Exception {
        stringToConvert = "";
        dateExpected = new LocalDate();

        dateConverted = DateConverter.convertFromStringToDate(stringToConvert);
    }


}
