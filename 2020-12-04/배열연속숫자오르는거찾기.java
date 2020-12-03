package com.study.day1203;

public class 배열연속숫자오르는거찾기 {
	public static void main(String[] args) {
		System.out.println(solution(5, new int[] {3}));
	}

	public static int solution(int X, int[] A) {
		boolean[] visited=new boolean[X+1];
		int cnt=0;
		
		for (int i = 0; i < A.length; i++) {
			if(!visited[A[i]]) { //방문하지않았다면
				visited[A[i]]=true;
				cnt++;
			}
			if(cnt==X) {
				return i;
			}
		}
		return -1;
	}
}
