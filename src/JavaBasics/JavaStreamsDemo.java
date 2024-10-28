package JavaBasics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.testng.annotations.Test;

public class JavaStreamsDemo {
	@Test
	public void regular() {

		List<String> names = new ArrayList<String>();
		names.add("Sameer");
		names.add("Abhijeet");
		names.add("Daya");
		names.add("Pradyuman");
		names.add("Sachin");
		names.add("Salunke");
		names.add("Freddy");
		names.add("tarika");
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).startsWith("S")) {
				System.out.println(names.get(i));
			}
		}
	}

	@Test
	public void streamFilter() {
		List<String> names = new ArrayList<String>();
		names.add("Sameer");
		names.add("Abhijeet");
		names.add("Daya");
		names.add("Pradyuman");
		names.add("Sachin");
		names.add("Salunke");
		names.add("Freddy");
		names.add("Tarika");

		Stream<String> filter = names.stream().filter(s -> s.startsWith("S"));
//		System.out.println(filter.toList().get(1));
		System.out.println(filter.count());

		List<String> namesList = Stream
				.of("Sameer", "Abhijeet", "Daya", "Pradyuman", "Sachin", "Salunke", "Freddy", "Tarika").filter(s -> {
					return s.startsWith("S");
				}).toList();
		System.out.println(namesList.size());

		Stream<String> newOp = names.stream().filter(s -> s.length() > 4 && s.length() <= 6);
		newOp.forEach(s -> System.out.println(s));

		Stream<String> newOp2 = names.stream().filter(s -> s.length() > 4 && s.length() <= 6);
		newOp2.limit(1).forEach(s -> System.out.println(s)); // --limit is terminal

	}

	@Test
	public void streamMap() {
		// Print names of defined length with Upper-case
		Stream<String> namesStream = Stream.of("Sameer", "Abhijeet", "Daya", "Pradyuman", "Sachin", "Salunke", "Freddy",
				"Tarika");
//		namesStream.filter(s->s.contains("n")).map(s-> s.toUpperCase()).forEach(s->System.out.println(s));
		namesStream.map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));

		Stream<String> namesStream2 = Stream.of("Sameer", "Abhijeet", "Daya", "Pradyuman", "Sachin", "Salunke",
				"Freddy", "Tarika");
		namesStream2.sorted().forEach(s -> System.out.println(s));

		List<String> names = Arrays.asList("Sameer", "Abhijeet", "Daya", "Pradyuman", "Sachin", "Salunke", "Freddy",
				"Tarika");
//		names.stream().sorted().forEach(s->System.out.println(s));

		List<String> names2 = Arrays.asList("Vivek", "Poorvi", "Shreya");
//		names2.stream().filter(s-> s.startsWith("D")).sorted().forEach(s->System.out.println(s));

		Stream<String> newStream = Stream.concat(names.stream(), names2.stream());
//		newStream.forEach(s->System.out.println(s));

		Boolean flag = newStream.anyMatch(s -> s.equalsIgnoreCase("Sameer"));
		System.out.println(flag);
	}

	@Test
	public void streamCollect() {
		Stream<String> namesStream = Stream.of("Sameer", "Abhijeet", "Daya", "Pradyuman", "Sachin", "Salunke", "Freddy",
				"Tarika");
		List<String> filteredNames = namesStream.filter(s -> s.startsWith("S")).map(s -> s.toUpperCase())
				.collect(Collectors.toList());
		filteredNames.forEach(s -> System.out.println(s));
		System.out.println(filteredNames.get(0));
	}

	@Test
	public void streamSorting() {

		List<String> names = Arrays.asList("Sameer", "Abhijeet", "Daya", "Pradyuman", "Sachin", "Salunke", "Freddy",
				"Tarika");
		names.stream().sorted().forEach(s -> System.out.println(s));

		String name = "sameer";
		// Sorting the characters of the name
		String sortedName = name.chars() // Convert the String into an IntStream of character codes
				.mapToObj(c -> (char) c) // Convert each character code into a Character object
				.sorted() // Sort the characters
				.map(String::valueOf) // Convert each character back to a String
				.collect(Collectors.joining()); // Join them into a single String
		System.out.println(sortedName);

//        String reversed = new StringBuilder(name).reverse().toString(); //--Not a stream example

		String reversed1 = Arrays.stream(name.split("")) // Split the string into an array of characters
				.collect(Collectors.toList()) // Collect them into a List
				.stream() // Stream the list again
				.sorted(Collections.reverseOrder()) // Reverse the order
				.collect(Collectors.joining()); // Join them back into a string

		System.out.println("Reversed: " + reversed1);

//		Number operations
		List<Integer> numbers = Arrays.asList(5, 3, 8, 1, 9, 2, 5);

		// Sorting the numbers
		numbers.stream().distinct().sorted().collect(Collectors.toList())// ---To make it a list
				.forEach(s -> System.out.println(s)); // ---distinct to remove duplicate numbers

		long number = 533245421;
		// Sorting the digits of the number
		String sortedDigits = String.valueOf(number) // Convert the number to a string
				.chars() // Convert the string to an IntStream of character codes
				.mapToObj(c -> (char) c) // Convert each character code to a Character object
				.sorted() // Sort the characters (digits)
				.distinct() // To remove repeated numbers
				.map(String::valueOf) // Convert each character back to a String
				.collect(Collectors.joining()); // Join them into a single string

		System.out.println("Sorted Digits: " + sortedDigits);
	}
}
