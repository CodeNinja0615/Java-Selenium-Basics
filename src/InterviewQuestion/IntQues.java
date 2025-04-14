package InterviewQuestion;

import java.util.ArrayList;
import java.util.List;

public class IntQues {

	public static void main(String[] args) {
		int[] name = {0,1,2,3,4,5,6};
		
		List<Integer> l1 = new ArrayList<Integer>();
		List<Integer> l2 = new ArrayList<Integer>();
		
		for(int i = 0; i<name.length; i++) {
			if(name[i] % 2== 0) {
				l2.add(name[i]);
			}
			else {
				l1.add(name[i]);
			}
		}

		System.out.println(l1);
		System.out.println(l2);

	}

}
