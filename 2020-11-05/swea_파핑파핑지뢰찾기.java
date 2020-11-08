package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_������������ã�� {
	public static int[] drow= {-1,-1,0,1,1,1,0,-1};
	public static int[] dcols= {0,1,1,1,0,-1,-1,-1};
	public static int N;
	public static String[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N=Integer.parseInt(br.readLine());
			ArrayList<int[]> clickList=new ArrayList<int[]>();
			map=new String[N][N];
			boolean [][] visited=new boolean[N][N];
			int maxCheck=0;
			for (int i = 0; i < N; i++) {
				String[] st=br.readLine().split("");
				for (int j = 0; j < st.length; j++) {
					if(st[j].equals(".")) {
						maxCheck++;
						clickList.add(new int[]{i,j});
					}else if(st[j].equals("*")) { // �����ִ°��� �̸� �湮üũ
						visited[i][j]=true;
					}
					map[i][j]=st[j];
				}
			}
			System.out.println(Arrays.deepToString(map));
			
			for (int[] c : clickList) {
				System.out.println(c[0]+" "+c[1]);
				go(c[0], c[1], visitedCopy(visited,N), maxCheck, 0);
				// 8���� Ȯ���ϰ� ���� ������
				// 8�������� ���� ����
			} 
			
		}
	}
	public static boolean[][] visitedCopy(boolean[][] origin,int N){
		boolean[][] newVisited=new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newVisited[i][j]=origin[i][j];
			}
		}
		return newVisited;
	}
	
	public static void go(int row,int cols,boolean[][] visited,int maxCheck,int count) {
		if(maxCheck==count) { // ���ĭ�� ���ڵ��� ǥ�õǾ�����
			return;
		}
		int bombCount=0;
		
		visited[row][cols]=true;
		for (int i = 0; i < drow.length; i++) { // �ȹ�Ž��
			int nrow=row+drow[i];
			int ncols=cols+dcols[i];
			
			if(nrow <0 || ncols <0 || nrow>=N || ncols>=N || visited[nrow][ncols]) { // 8��Ž���ؼ�
				continue; // ���� ����ٸ�
			}
			if(map[nrow][ncols].equals("*")) {
				go(nrow,ncols,visited,maxCheck,count+1);
			}
			bombCount++;
		}
		
		if(bombCount==8) {
			
		}
	}
}
