package util.test;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.DateConverter;

public class DateConverterTest {
	
	private final LocalDate[] DATES = new LocalDate[]{
		new LocalDate(1991, 12, 12),
		new LocalDate(1991, 12, 2),	
		new LocalDate(1991, 2, 12),	
	};
	
	private final String[] DATES_STRING = new String[]{
			"1991-12-12",
			"1991-12-2",	
			"1991-2-12",
			"1991-12-02",	
			"1991-02-12",	
		};

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertLocalDate() {
		System.out.println("---testConvertLocalDate()---");
		for(LocalDate e : DATES) {
			System.out.println(DateConverter.convert(e));
		}
	}

	@Test
	public void testConvertString() {
		System.out.println("---testConvertString()---");
		for(String e : DATES_STRING) {
			System.out.println(DateConverter.convert(e));
		}
	}

}
