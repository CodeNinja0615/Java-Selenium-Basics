package InterviewQuestion;

import java.util.ArrayList;
import java.util.List;

//You are given a string text that contains a mix of digits and alphabets. 
//Your task is to output the frequency of each unique character in the string in decreasing order.
//Make sure to not use the inbuilt function "Counter".
//
//Input: 9bccdd27cdf841918a12c753fe
//Output:
//Character "c" has occured 3 times
//Character "d" has occured 3 times
//....
//Character "f" has occurred 2 times
public class EduGorillaInterviewQue {

	public static void main(String[] args) {
		String str = "9bccdd27cdf841918a12c753fe";
		List<Character> ls = new ArrayList<Character>();
		int count = str.length();

		for (int i = 0; i < count; i++) {
			int countNum = 0;
			char st = str.charAt(i);
			if (ls.contains(str.charAt(i))) {
				continue;
			}
			ls.add(str.charAt(i));
			if (ls.contains(str.charAt(i)))
				;
			for (int j = 0; j < count; j++) {
				if (st == str.charAt(j)) {
					countNum++;
				}

			}
			if (countNum > 1) {
				System.out.println("Character " + str.charAt(i) + " has occured " + countNum + " times");
			}
		}

	}

}
