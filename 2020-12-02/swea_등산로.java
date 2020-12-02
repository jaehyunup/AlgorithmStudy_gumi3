package com.study.day1202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class swea_등산로 {
	static int drow[] = { -1, 0, 1, 0 };
	static int dcols[] = { 0, 1, 0, -1 };
	static int answer;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] NK = br.readLine().split(" ");
			int N = Integer.parseInt(NK[0]);
			int K = Integer.parseInt(NK[1]);
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			int maxValue = -999;
			answer = 0;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					maxValue = Math.max(maxValue, map[i][j]);
				}
			}
			List<Point> top = new ArrayList<Point>();
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j] == maxValue) {
						top.add(new Point(i, j));
					}
				}
			}
			for (int i = 0; i < top.size(); i++) {
//				System.out.println("=============");
				go(top.get(i), map,new boolean[N][N], 1, K);
			}
			//System.out.println("=======끝========");
			System.out.println("#"+test_case+" "+answer);
		}
	}

	public static void go(Point p, int[][] map,boolean[][] visited, int count, int K) {
		int row = p.getRow();
		int cols = p.getCols();
		int curHeight = map[row][cols];
		visited[row][cols]=true;
		if(Math.max(answer,count)==count) {
			answer=count;
		}
		
		
		for (int i = 0; i < 4; i++) {
			int nrow = row + drow[i];
			int ncols = cols + dcols[i];
			if (nrow < 0 || nrow >= map.length || ncols < 0 || ncols >= map.length|| visited[nrow][ncols]==true) { // 범위체크
				continue; // 버림
			}
			int nHeight = map[nrow][ncols];
			if (curHeight <= nHeight) { // 깎아야 진행 가능하다면
				if (K > 0) { // 깎을 수 있을때
					for (int j = 1; j <= K; j++) { // 깎을수 있는 경우의 수 1,2,3,4
						if( curHeight <=(nHeight-j)) { // 깎았을때 진행이 불가능하다면
							continue;
						}
						// 깎았을때 진행 가능하다면
						int[][] nMap=copyMap(map);
						nMap[nrow][ncols]=nHeight-j; // 깎은상태의 맵을 새로 만들어서 출발
						visited[nrow][ncols]=true;
						go(new Point(nrow,ncols),nMap,visited,count+1,0);
						visited[nrow][ncols]=false;
					}
				}
			} else { // 그냥 진행할 수 있다면 그냥 진행
				visited[nrow][ncols]=true;
				go(new Point(nrow,ncols),map,visited,count+1,K);
				visited[nrow][ncols]=false;
			}
		}
		return;
	}

	public static int[][] copyMap(int[][] map){
		int[][] newMap=new int[map.length][map.length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				newMap[i][j]=map[i][j];
			}
		}
		return newMap;
	}
	public static class Point {
		public int row;
		public int cols;

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

		@Override
		public String toString() {
			return "Point [row=" + row + ", cols=" + cols + "]";
		}

		public Point(int row, int cols) {
			this.row = row;
			this.cols = cols;
		}

		public Point() {
		}

	}
}
