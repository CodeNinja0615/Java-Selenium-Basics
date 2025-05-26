package javaBasics;

import java.util.HashSet;

public class HashsetDemo {

	public static void main(String[] args) {

		HashSet<String> hs = new HashSet<String>();
		hs.add("Sameer");
		hs.add("Sameer2");
		hs.add("Akhtar");
		hs.add("Akhtar");
		System.out.println(hs);
	}

}
