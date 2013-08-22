package util;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateConverter {

    private static final char HQSQL_DATE_SEPARATOR = '-';
    private static final String HQSQL_DATE_SEPARATOR_REGEX = "[" + HQSQL_DATE_SEPARATOR + "]";
    private static final char COMMON_DATE_SEPARATOR = '/';

    private static final int YEAR = 0;
    private static final int MONTH = 1;
    private static final int DAY = 2;

    public final static DateTimeFormatter EUROPEAN_DATE_TIME_FORMAT = DateTimeFormat.forPattern("d' 'MMMM' 'yyyy', ore 'HH':'mm':'ss");
    public final static DateTimeFormatter EUROPEAN_DATE_FORMAT = DateTimeFormat.forPattern("d' 'MMMM' 'yyyy");
    public final static DateTimeFormatter EUROPEAN_TIME_FORMAT = DateTimeFormat.forPattern("HH':'mm':'ss");
    public final static DateTimeFormatter NORMAL_DATE_FORMAT = DateTimeFormat.forPattern("dd'/'MM'/'yyyy");

    private DateConverter() {

    }

    public static String convert(LocalDate date) {
        String dateString = date.toString();

        return convertHQSQLDateFormat(dateString);
    }

    public static LocalDate convert(String dateString) {
        String[] splittedDateString = dateString.split(HQSQL_DATE_SEPARATOR_REGEX);

        Integer year = new Integer(splittedDateString[YEAR]);
        Integer month = new Integer(splittedDateString[MONTH]);
        Integer day = new Integer(splittedDateString[DAY]);

        return new LocalDate(year, month, day);
    }

    private static String convertHQSQLDateFormat(String dateString) {
        return dateString.replace(COMMON_DATE_SEPARATOR, HQSQL_DATE_SEPARATOR);
    }
}
