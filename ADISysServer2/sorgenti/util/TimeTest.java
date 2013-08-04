package util;

import org.joda.time.LocalTime;
import org.joda.time.TimeOfDay;

public class TimeTest {
	/*
	 * Qui il discorso relativo al numero dei parametri
	 * si fa un pochino complicato perché Time deve essere
	 * immutabile, altrimenti ci possiamo incappare in
	 * spiacevoli conseguenze.
	 */
	
	public static void main(String... args) {
		LocalTime tempo = new LocalTime(12, 24, 45);
		System.out.println(tempo);
	}
}
