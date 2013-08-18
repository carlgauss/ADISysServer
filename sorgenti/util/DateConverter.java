package util;

import org.joda.time.LocalDate;

public class DateConverter {

	private static final char HQSQL_DATE_SEPARATOR = '-';
	private static final String HQSQL_DATE_SEPARATOR_REGEX = "[" + HQSQL_DATE_SEPARATOR + "]";
	private static final char COMMON_DATE_SEPARATOR = '/';
	
	private static final int YEAR = 0;
	private static final int MONTH = 1;
	private static final int DAY = 2;
	
	
	private DateConverter() {
		// TODO Auto-generated constructor stub
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
