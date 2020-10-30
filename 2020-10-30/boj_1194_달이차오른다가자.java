package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj_1194_�����������ٰ��� {
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
/*	F�� �԰� F ����� �ⱸ���ִٸ� ��������. */
/*	����� �ⱸ�� Ű�� ã��, */
/* �ֿ켱������ �����Ұ��� ����*/
/* ������ϴ� ��� ���� �켱 Ȯ���Ѵ�.(������ Ű�� ������ ������ �� �ִ� �ִܰŸ�) */
/* �̶� ������ ���� Ȯ���ϰ�,Ű�� ������� ã�´� 
 * */

	public static int bfs(Point p, String[][] map, boolean[][][] visited) { // ������Ű ã��
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
						&& !map[nrow][ncols].equals("#")) { // �̵������ϴٸ�
					char t=map[nrow][ncols].charAt(0);
					
					/*���̶�� Ű �������ִٴ°� ���� */
					if( t >='A' && t<='F') { // ���̶�� �����ִ��� Ȯ��
						if( (nkey & (1 << t-'A'))== 0) { // Ű���Ȱ������ִٸ�
							// ���� ��ġ�Ѱ��� �ʿ��� key�� ��. 1�� 2^0=true�� 000001��
							// 000001���� �������� t-'A' ��ŭ�����̸�, A�϶��� 000001 �״��
							// B�϶��� 000010 �̷������� �ε����� ǥ���ϰ� ��
							continue;
						}
					}
					
					/*������ ���� ���谪�� ������Ʈ����(or��������)*/
					if(t >='a' && t<='f') { // ������
						nkey=(nkey| 1 << t-'a'); // or������ ���� �ش� Ű �ε������� �÷���.
					}
					
					/*�������� �����ϱ��� ������ �湮üũ*/
					if(visited[nrow][ncols][nkey]) { // �������踦 ������ �湮�ߴٸ�
						continue;
					}
					/*�������� ����Ű�� ��� �湮���� �ʾҴٸ� ����*/
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
