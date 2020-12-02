package com.study.day1202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class swea_최장경로 {
	static int answer = 0;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] NM = br.readLine().split(" ");
			int N = Integer.parseInt(NM[0]);
			int M = Integer.parseInt(NM[1]);
			ArrayList<Integer> lines[] = new ArrayList[N + 1];
			answer=0;
			if (M == 0) {
				System.out.println("#" + test_case + " " + 1);
				continue;
			}
			for (int i = 0; i < M; i++) {
				int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				if (lines[line[0]] == null) {
					lines[line[0]] = new ArrayList<Integer>();
				}
				if (lines[line[1]] == null) {
					lines[line[1]] = new ArrayList<Integer>();
				}
				lines[line[0]].add(line[1]);
				lines[line[1]].add(line[0]);
			}
			for (int i = 1; i < lines.length; i++) {
				// i = 시작 정점
				if (lines[i] != null) {
					for (int j = 0; j < lines[i].size(); j++) {
						boolean[] visited = new boolean[N + 1];
						visited[i] = true;
						go(lines, lines[i].get(j), 1, visited); // 시작정점(i)에서 갈수있는 모든 정점의 리스트 순환
					}
				}
			}
			System.out.println("#" + test_case + " " + answer);
		}
	}

	public static void go(ArrayList<Integer>[] lines, int vertex, int count, boolean[] visited) {
		if (Math.max(answer, count) == count) {
			answer = count;
		}

		for (int i = 0; i < lines[vertex].size(); i++) { // 해당 정점에서 갈수있는 모든 vertex 가보기
			if (visited[vertex]) {
				continue;
			}
			visited[vertex] = true;
			go(lines, lines[vertex].get(i), count + 1, visited);
			visited[vertex] = false;
		}
	}
}
