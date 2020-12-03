package com.study.day1203;

public class 최소점프찾기 {
	public static void main(String[] args) {
		System.out.println(solution(10, 85, 30));
	}

	public static int solution(int X, int Y, int D) {
		return (int) Math.ceil((double)(Y - X) / D);
	}
}
