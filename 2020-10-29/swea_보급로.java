package com.study.day1029;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class swea_보급로2 {
	public static int N;
	public static int[] drow = { -1, 1, 0, 0 };
	public static int[] dcols = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int[][] dv = new int[N][N];

			boolean[][] visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			}

			System.out.println("#"+test_case+" "+check(map, visited, new Point(0, 0, 0)));
		}

	}

	public static int check(int[][] map, boolean[][] visited, Point p) {
		PriorityQueue<Point> q = new PriorityQueue<Point>();
		q.offer(p);
		while (!q.isEmpty()) {
			Point cp = q.poll();
			visited[cp.row][cp.cols]=true;
//			System.out.println(cp);
			if (cp.row == N - 1 && cp.cols == N - 1) {
				return cp.getDist();
			}
			/*4방 탐색으로 가장 낮은 가중치의 친구를 삽입*/
			for (int i = 0; i < drow.length; i++) {
				int nRow = cp.row + drow[i];
				int nCols = cp.cols + dcols[i];
				if (nRow >= 0 && nRow < N && nCols >= 0 && nCols < N && !visited[nRow][nCols]) { // 범위 안일때
					/* 갈수있는 모든곳에대해 가중치를 확인 */
					Point np=new Point(nRow,nCols,cp.getDist()+map[nRow][nCols]);
					q.offer(np);
				}

			}
		}
		return 0;
	}

	public static class Point implements Comparable<Point> {
		int row;
		int cols;
		int dist;

		public Point() {
		}

		public Point(int row, int cols, int dist) {
			this.row = row;
			this.cols = cols;
			this.dist = dist;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCols() {
			return cols;
		}

		public void setCols(int cols) {
			this.cols = cols;
		}

		public int getDist() {
			return dist;
		}

		public void setDist(int dist) {
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Point [row=" + row + ", cols=" + cols + ", dist=" + dist + "]";
		}

		@Override
		public int compareTo(Point o) {
			return this.dist - o.dist;
		}

	}
}