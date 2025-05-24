package javaBasics;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {

	public static void main(String[] args) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		String date = sdf.format(d);
		String date2 = sd.format(d);
		System.out.println(date);
		System.out.println(date2);
		System.out.println(d);

	}

}
