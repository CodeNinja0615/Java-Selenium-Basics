package javaBasics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapDemo {

	public static void main(String[] args) {
		Map<String, Integer> hm = new HashMap<String, Integer>();
		hm.put("Sameer", 2);
		hm.put("Akhtar", 1);
		hm.put("King", 4);
		hm.put("Wild", 3);
		System.out.println(hm);
		System.out.println(hm.getOrDefault("Sameer", 0));
		Set<Entry<String, Integer>> s = hm.entrySet();
		Iterator<Entry<String, Integer>> it = s.iterator();
		while (it.hasNext()) {
			Map.Entry mp = (Map.Entry) it.next();
			System.out.println(mp.getKey());
			System.out.println(mp.getValue());

		}

	}

}
