package com.study.day1203;

import java.util.stream.IntStream;

public class 누락된수찾기 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {2,3,1,5}));
	}

	public static int solution(int[] A) {
		int N=A.length+1;
		long sum=(long)(N+1)*N/2;
        return (int) (sum-(long)IntStream.of(A).sum());
    }
}
