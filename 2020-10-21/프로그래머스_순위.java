package com.study.day1021;

import java.util.Arrays;
import java.util.Comparator;

public class 프로그래머스_순위 {
	public static class solution {
		public int solution(int n, int[][] results) {
			int[][] map = new int[n + 1][n + 1];
			for (int i = 1; i < map.length; i++) {
				for (int j = 1; j < map[i].length; j++) {
					map[i][j] = (i != j) ? Integer.MAX_VALUE : 0;
				}
			}

			Arrays.sort(results ,(arr1,arr2) ->  (arr1[0]==arr2[0])? arr1[1]-arr2[1]:arr1[0]-arr2[0] );
			
				
			
			
			System.out.println(Arrays.deepToString(results));
			int answer = 0;
			return answer;
		}
	}

	public static void main(String[] args) {
		int n = 5;
		int[][] results = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };
		new solution().solution(n, results);
	}
}
