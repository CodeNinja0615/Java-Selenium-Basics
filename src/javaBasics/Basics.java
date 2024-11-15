package javaBasics;

import java.util.Arrays;
import java.util.List;

public class Basics {

	public static void main(String[] args) {
		
		int[] arr = new int[5];
		
		arr[0] = 1;
		arr[1] = 3;
		arr[2] = 4;
		arr[3] = 6;
		arr[4] = 8;
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		
		String [] strArr = {"Sameer", "Akhtar", "is", "the", "best."};
		System.out.println(strArr[4]);
		
		List<String> arrList = Arrays.asList(strArr);
		System.out.println(arrList.get(1));
		
		String name = "Sameer Akhtar";
		
		String[] S0 = name.split(" ");
		
		String s1 = S0[0];
		String s2 = S0[1];
		

		System.out.println(s1);
		System.out.println(s2);
		
		for(int n = name.length()-1; n >= 0; n--) {
			System.out.print(name.charAt(n));
		}
		
		
		
	}

}
