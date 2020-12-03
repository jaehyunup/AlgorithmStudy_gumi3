package com.study.day1203;

public class 배열두개영역합친값비교 {
	public static void main(String[] args) {
		
		System.out.println(solution(new int[] { -1000,1000}));
	}

	public static int solution(int[] A) {
		int answer=Integer.MAX_VALUE;
		int leftBlock=A[0];
		int rightBlock=0;
		for (int i = 1; i < A.length; i++) { // 합
			rightBlock+=A[i];
		}
		answer=Math.min(answer,Math.abs(leftBlock-rightBlock));
		for (int i = 1; i <A.length-1; i++) {
			leftBlock+=A[i];
			rightBlock-=A[i];
			answer=Math.min(answer,Math.abs(leftBlock-rightBlock));
		}
		return answer==Integer.MAX_VALUE?0:answer;
	}
	
}
