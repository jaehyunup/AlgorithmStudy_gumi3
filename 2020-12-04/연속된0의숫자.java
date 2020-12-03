package com.study.day1203;

public class 연속된0의숫자 {
	public static void main(String[] args) {
		System.out.println(solution(1041));
	}
	public static int solution(int N) {
		String t= Integer.toBinaryString(N);
		int answer=0;
		int count=0;
		for (String num : t.split("")) {
			if(num.equals("0")) {
				++count;
			}else {
				answer=Math.max(answer,count);
				count=0;
			}
		}
		return answer;
	}
}
