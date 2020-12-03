package com.study.day1203;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class 배열굴리기 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {}, 3)));
	}

	public static int[] solution(int[] A, int K) {
		Deque<Integer> dq = new LinkedList<Integer>();
		if (A.length == 0) {
			return A;
		}
		for (int num : A) {
			dq.offerLast(num);
		}
		for (int i = 0; i < K; i++) {
			dq.offerFirst(dq.pollLast());
		}
		return Arrays.stream(dq.toArray(new Integer[A.length])).mapToInt(i -> i).toArray();
	}
}
