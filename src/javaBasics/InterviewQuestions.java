package javaBasics;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

public class InterviewQuestions {

	@Test
	public void swapVariables() {
		int a = 5;
        int b = 10;

        System.out.println("Before Swap: a = " + a + ", b = " + b);

        // Swap using XOR
        a = a ^ b;  // Step 1
        b = a ^ b;  // Step 2
        a = a ^ b;  // Step 3

        System.out.println("After Swap: a = " + a + ", b = " + b);
	}
	
	@Test
	public void stringReverse() {
		String input = "I am Sameer Akhtar";

        // Reverse the entire string using Streams
        String reversed = IntStream.range(0, input.length())
                                   .mapToObj(i -> input.charAt(input.length() - 1 - i))
                                   .map(Object::toString)
                                   .collect(Collectors.joining());

        System.out.println(reversed);
	}

	@Test
	public void palindrome() {
		String input = "A man a plan a canal Panama";

		// Normalize the input by removing spaces and converting to lowercase
		String normalizedInput = input.replaceAll("\\s+", "").toLowerCase();

		// Check if the string is a palindrome
		boolean isPalindrome = normalizedInput.equals(IntStream.range(0, normalizedInput.length())
				.mapToObj(i -> normalizedInput.charAt(normalizedInput.length() - 1 - i)).map(Object::toString)
				.collect(Collectors.joining()));

		System.out.println("Is the input a palindrome? " + isPalindrome);

	}
}
