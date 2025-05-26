package javaBasics;

import java.util.ArrayList;

public class ArrayListDemo {

	public static void main(String[] args) {
		ArrayList<String> as = new ArrayList<String>();
		as.add("apple");
		as.add("banana");
		as.add("cherry");
		as.add("mango");
		as.add("apple");
		System.out.println(as);
		as.remove("apple");
		System.out.println(as);
		if (as.contains("orange")) {
			System.out.println("orange exists");
		}
		int size = as.size();
		System.out.println(size);
		for (int i = 0; i < size; i++) {
			System.out.println("index: " + i + " value: " + as.get(i));
		}
	}

}
