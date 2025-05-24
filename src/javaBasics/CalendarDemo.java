package javaBasics;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarDemo {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		String ca = sd.format(cal.getTime());
		System.out.println(ca);
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
	}

}
