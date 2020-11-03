package com.study.day1103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class swea_탈주범검거 {
	static int N,M,R,C,L;
	static boolean[][] visited;
	static int[][] map;
	static int[][] drowArr= {{},{-1,1,0,0},{-1,1},{0,0},{-1,0},{1,0},{1,0},{-1,0} };
	static int[][] dcolsArr= {{},{0,0,-1,1},{0,0},{-1,1},{0,1},{0,1},{0,-1},{0,-1} };
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int NMRCL[]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			N=NMRCL[0];
			M=NMRCL[1];
			R=NMRCL[2];
			C=NMRCL[3];
			L=NMRCL[4];
			answer=0;
			map=new int[N][M];
			visited=new boolean[N][M];
			for (int i = 0; i < N; i++) {
				map[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			bfs(R,C);
			for (int i = 0; i < visited.length; i++) {
				for (int j = 0; j < visited[i].length; j++) {
//					System.out.print(visited[i][j]+" ");
					if(visited[i][j]==true){
						answer++;
					}
				}
//				System.out.println();
			}
			System.out.println("#"+test_case+" "+(answer));
		}
	}
	public static void bfs(int r,int c) {
		Queue<Point> q=new LinkedList<Point>();
		Point p=new Point(r,c,1);
		visited[r][c]=true;
		q.offer(p);
		while(!q.isEmpty()) {
			Point cp=q.poll();
			if(cp.t >= L) {
				return;
			}
			int[] drow=drowArr[map[cp.row][cp.cols]];
			int[] dcols=dcolsArr[map[cp.row][cp.cols]];
			/*현재 위치에 설치된 터널을 통해서 갈수있는 방향을 모두 탐색*/
			for (int i = 0; i < drow.length; i++) {
				int nrow=cp.row+drow[i];
				int ncols=cp.cols+dcols[i];
				/* 범위를 벗어나거나, 이미 방문했거나, 다음 터널이 설치되어있지 않을때*/
				if(nrow<0 || ncols<0 || nrow>=N || ncols >=M || visited[nrow][ncols] || map[nrow][ncols] == 0) {
					continue;
				}
				/* 다음위치에 터널이 있고, 다음 터널이 현재 터널과 연결되어있는지*/
				// 다음 위치에서 갈 수 있는 터널 방향 모두
				int[] ndrow=drowArr[map[nrow][ncols]]; // 다음 터널의 터널타입
				int[] ndcols=dcolsArr[map[nrow][ncols]]; // 다음 터널의 터널타입
				for (int j = 0; j < ndrow.length; j++) { // 현재 터널의 drow,dcols와  다음 터널의 ndrow,ncols의 합이 0이되면 진출가능
					if(ndrow[j]+drow[i]==0&& ndcols[j]+dcols[i]==0) { // 현재 터널과 다음 터널이 연결되어있다면
						visited[nrow][ncols]=true;
						q.offer(new Point(nrow,ncols,cp.t+1));
					}
				}
			}
		}
	}

	public static class Point{
		int row;
		int cols;
		int t;
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
		public Point(int row, int cols) {
			this.row = row;
			this.cols = cols;
		}
		public Point() {
		}
		
		public int getT() {
			return t;
		}
		public void setT(int t) {
			this.t = t;
		}
		
		public Point(int row, int cols, int t) {
			this.row = row;
			this.cols = cols;
			this.t = t;
		}
		@Override
		public String toString() {
			return "Point [row=" + row + ", cols=" + cols + ", t=" + t + "]";
		}
		
	}
}
