package com.study.day1203;

import java.util.HashMap;
import java.util.Map.Entry;

public class 짝이없는글자찾기 {
	public static void main(String[] args) {
		System.out.println((solution(new int[] {9,3,9,3,9,7,9})));
	}

	public static int solution(int[] A) {
		HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
		for (int i = 0; i < A.length; i++) {
			if (m.containsKey(A[i])) {
				m.put(A[i], m.get(A[i]) + 1);
			} else {
				m.put(A[i], 1);
			}
		}
		for (Entry<Integer, Integer> t : m.entrySet()) {
			if (t.getValue()%2==1 ) { 
				return t.getKey();
			}
		}
		return 0;
	}
}
