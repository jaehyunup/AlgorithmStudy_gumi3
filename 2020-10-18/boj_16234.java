package com.study.day1018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj_16234 {
	static int[] drow = { -1, 1, 0, 0 };
	static int[] dcols = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		/*
		 * �Ϸ翡 �ѹ� ���ĭ�� ���� bfs�� �ؼ� �� ������ ���س���,�α��̵��� �����Ѵ�. ����, ������ �ϳ��� ���ٸ� �����Ѵ�.
		 */
		Scanner sc = new Scanner(System.in);
		String[] NLR = sc.nextLine().split(" ");
		int N = Integer.parseInt(NLR[0]);
		int L = Integer.parseInt(NLR[1]);
		int R = Integer.parseInt(NLR[2]);
		int[][] map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
			sc.nextLine();
		}
		System.out.println("==============");
		int answer=0;
		while (true) {
			int dayCount=0;
			boolean[][] visited = new boolean[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dayCount+=personMove(i,j,map, visited,L, R);
					for (int j2 = 1; j2 < visited.length; j2++) {
						for (int k = 1; k < visited.length; k++) {
							System.out.print(map[j2][k]+" ");
						}
						System.out.println();
					}
					System.out.println("=======");
				}
			}
			System.out.println(dayCount);
			if(dayCount==0) {
				break;
			}else {
				answer++;
			}
			
		}
		System.out.println(answer);
	}

	public static int personMove(int row,int cols,int[][] map, boolean[][] visited, int L, int R) {
		int personCount = map[row][cols];
		int stateCount = 1;
//		System.out.println("[����] "+row+" , "+cols);
		Queue<state> q = new LinkedList<state>();
		ArrayList<state> groupList=new ArrayList<state>();
		visited[row][cols] = true;
		state startState = new state();
		startState.setRow(row);
		startState.setCols(cols);
		groupList.add(startState);
		q.offer(startState);
		while (!q.isEmpty()) {
			state cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nextRow = cur.row + drow[i];
				int nextCols = cur.cols + dcols[i];
				if (nextRow <= 0 || nextRow >= map.length || nextCols <= 0 || nextCols >= map.length
						|| visited[nextRow][nextCols] == true) {// ��������ٸ�
					continue;
				} else {
					int sDiff = Math.abs(map[cur.row][cur.cols] - map[nextRow][nextCols]); // �α���
					if (sDiff >= L && sDiff <= R) { // �α����� L���̻�,R������ ���
						visited[nextRow][nextCols] = true;
						state nextState = new state();
						System.out.println("[cur]"+cur.row+" , "+cur.row+" Q[row,cols] = "+nextRow+" , "+nextCols+" sdiff:"+sDiff);
						nextState.setRow(nextRow);
						nextState.setCols(nextCols);
						personCount+=map[nextRow][nextCols];
						stateCount++;
						groupList.add(nextState);
						q.offer(nextState);
					}else {
						continue;
					}
				}
			}
		}
		if(groupList.size()<2) { // �α��̵��� ���ٸ�
			return 0;
		}
		int ever=personCount/stateCount;
		
		for (int i = 0; i < groupList.size(); i++) {
			state groupState=groupList.get(i);
			map[groupState.getRow()][groupState.getCols()]=personCount/stateCount;
		}
		System.out.println("���α� : "+personCount+"���ձ����� : "+stateCount+" ���: "+ever);
		return groupList.size(); // �α��̵��� ������
	}

	public static class state {
		int row;
		int cols;
		boolean status;
		int group;

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

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		public int getGroup() {
			return group;
		}

		public void setGroup(int group) {
			this.group = group;
		}

		public state() {
		}

		public state(int row, int cols, boolean status, int group) {
			super();
			this.row = row;
			this.cols = cols;
			this.status = status;
			this.group = group;
		}

		@Override
		public String toString() {
			return "state [row=" + row + ", cols=" + cols + ", status=" + status + ", group=" + group + "]";
		}

	}
}
