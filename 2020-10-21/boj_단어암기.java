package com.study.day1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_단어암기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]); // 단어개수
		int M = Integer.parseInt(NM[1]); // 커맨드개수
		int[] word = new int[N]; 
		int brain= (1 << 27)-1; // 26번째 비트까지 1인 비트 mask (처음엔 모든 알파벳을 알고있다);
		
		for (int i = 0; i < N; i++) {
			int temp=0; // 단어 비트
			char[] line = br.readLine().toCharArray();
			for (char c : line) {
				temp = temp |  1 << (c-'a'); 
			}
			word[i]=temp;
			
		}
//		System.out.println(Arrays.toString(word));
		
		/* 커맨드로 들어온 알파벳은 기억하지 못하기때문에 모든곳에서 삭제한다 */
		/* doc의 row을 와 현재 row를 비교한다 */
		
		for (int i = 0; i < M; i++) {
			String com[]=br.readLine().split(" ");
			if(com[0].equals("1")) { // 잊어먹기
				 brain=brain ^ 1 << ( com[1].charAt(0)-'a');
			}else { // 기억하기
				 brain= brain | 1 << ( com[1].charAt(0)-'a'); // and연산을 통해 해당 알파벳 자리를 1로 하기위해 and연산 
			}
			int count=0;
			for (int j = 0; j < N; j++) {
				if(word[j]==(word[j] & brain)) {
					count++;
				}
			}
			System.out.println(count);
		}

	}
}
