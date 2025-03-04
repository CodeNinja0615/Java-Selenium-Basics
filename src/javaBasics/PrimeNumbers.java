package javaBasics;

import java.util.Scanner;

public class PrimeNumbers {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number");
		int num = sc.nextInt();
		int temp = 0;
		for (int i = 2; i < num; i++) {
			if (num % i == 0 && i < num) {
				temp++;
			}
		}
		if (temp >= 1) {
			System.out.println("Not prime");
		} else {
			System.out.println("Prime");
		}
	}
}
