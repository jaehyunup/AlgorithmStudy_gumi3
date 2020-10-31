package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj_1194_달이차오른다가자 {
	static int[] drow = { -1, 1, 0, 0 };
	static int[] dcols = { 0, 0, -1, 1 };
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] rc = br.readLine().split(" ");
		N = Integer.parseInt(rc[0]);
		M = Integer.parseInt(rc[1]);
		int minsikRow = 0;
		int minsikCols = 0;
		boolean[][][] visited = new boolean[N][M][64];
		String[][] map = new String[N][M];
		for (int i = 0; i < N; i++) {
			String[] li = br.readLine().split("");
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = li[j];
				if (map[i][j].equals("0")) {
					minsikRow = i;
					minsikCols = j;
				}
			}
		}
		int answer=bfs(new Point(minsikRow, minsikCols,0,0), map, visited);
		System.out.println(answer==-1?-1:answer);
	}
/*	F를 먹고 F 가까운 출구가있다면 나가야지. */
/*	가까운 출구나 키를 찾고, */
/* 최우선적으로 진출할곳을 선택*/
/* 열어야하는 모든 문을 우선 확인한다.(마스터 키를 가지고 도착할 수 있는 최단거리) */
/* 이때 지나간 문을 확인하고,키를 순서대로 찾는다 
 * */

	public static int bfs(Point p, String[][] map, boolean[][][] visited) { // 마스터키 찾기
		Queue<Point> q = new LinkedList<Point>();
		visited[p.row][p.cols][0]=true;
		q.offer(p);
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if(map[cur.row][cur.cols].equals("1")) {
				return cur.count;
			}
			for (int i = 0; i < drow.length; i++) {
				int nrow = cur.row + drow[i];
				int ncols = cur.cols + dcols[i];
				int nkey= cur.key;
				if (nrow >= 0 && nrow < N && ncols >= 0 && ncols < M 
						&& !map[nrow][ncols].equals("#")) { // 이동가능하다면
					char t=map[nrow][ncols].charAt(0);
					
					/*문이라면 키 가지고있다는게 증명 */
					if( t >='A' && t<='F') { // 문이라면 열수있는지 확인
						if( (nkey & (1 << t-'A'))== 0) { // 키를안가지고있다면
							// 문이 위치한곳에 필요한 key의 값. 1은 2^0=true로 000001임
							// 000001에서 왼쪽으로 t-'A' 만큼움직이면, A일때는 000001 그대로
							// B일때는 000010 이런식으로 인덱스를 표현하게 됨
							continue;
						}
					}
					
					/*열쇠라면 현재 열쇠값을 업데이트해줌(or연산으로)*/
					if(t >='a' && t<='f') { // 열쇠라면
						nkey=(nkey| 1 << t-'a'); // or연산을 통해 해당 키 인덱스값을 올려줌.
					}
					
					/*다음으로 진행하기전 마지막 방문체크*/
					if(visited[nrow][ncols][nkey]) { // 같은열쇠를 가지고 방문했다면
						continue;
					}
					/*같은곳에 같은키를 들고 방문하지 않았다면 진행*/
					visited[nrow][ncols][nkey]=true;
					q.offer(new Point(nrow,ncols,nkey,cur.count+1));
				}else {
					continue;
				}
			}

		}
		return -1;
	}
	public static class Point {
		int row;
		int cols;
		int key;
		int count;
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
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
		public Point(int row, int cols) {
			this.row = row;
			this.cols = cols;
		}
		public Point() {
		}
		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
		public Point(int row, int cols, int key, int count) {
			this.row = row;
			this.cols = cols;
			this.key = key;
			this.count = count;
		}
		
	}
}
