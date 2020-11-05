package com.study.day1105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class boj_2564_경비원 {
	static int[][] map;
	static int R, C, N;
	static int[] Rdrow = { 0, 0, 0, 1, -1 };
	static int[] Rdcols = { 0, -1, 1, 0, 0 };
	public static void main(String[] args) throws IOException {
		/* 북남서동 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] CR = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		R = CR[1];
		C = CR[0];
		map = new int[R + 1][C + 1];
		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			String[] l = br.readLine().split(" ");
			makeMap(Integer.parseInt(l[0]), Integer.parseInt(l[1]), i);
		}

		int[] dg = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] cur = makeMap(dg[0], dg[1], Integer.MAX_VALUE);
		int answer = 0;
		for (int i = 1; i <= N; i++) { // i 가게 찾기
			int length = (R * 2) + (C * 2);
			int rCnt = rightDfs(cur[0], cur[1], cur[2], i);
			answer += Math.min(rCnt, length - rCnt);
		}
		System.out.println(answer);
	}

	public static int rightDfs(int row, int cols, int direction, int company) {// 반시계
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { row, cols, direction, 0 });
		while (!q.isEmpty()) {
			int[] cp = q.poll();
			if (map[cp[0]][cp[1]] == company) {
				return cp[3];
			}
			int nrow = cp[0] + Rdrow[cp[2]];
			int ncols = cp[1] + Rdcols[cp[2]];
			if (nrow < 0 || ncols < 0 || nrow > R || ncols > C) { // 범위벗어난다면 방향변경
				int nd = cp[2] + 2;
				if (nd == 6) {
					nd = 1;
				} else if (nd == 5) {
					nd = 2;
				}
				q.offer(new int[] { cp[0], cp[1], nd, cp[3] });
				continue;
			}
			q.offer(new int[] { nrow, ncols, cp[2], cp[3] + 1 });
		}
		return 0;
	}

	public static int[] makeMap(int loc, int len, int i) {
		int row = 0, cols = 0;
		switch (loc) {
		case 1: // 북
			row = 0;
			cols = len;
			map[0][len] = i;
			break;
		case 2: // 남
			row = R;
			cols = len;
			map[R][len] = i;
			break;
		case 3: // 서
			row = len;
			cols = 0;
			map[len][0] = i;
			break;
		case 4: // 동
			row = len;
			cols = C;
			map[len][C] = i;
			break;
		}
		return new int[] { row, cols, loc };
	}
}
