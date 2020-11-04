package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class swea_5643_키순서 {
	public static int[][] map;
	public static int[][] d;
	public static int INF=-1;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N=Integer.parseInt(br.readLine()); // 학생수
			d=new int[N+1][N+1];
			map=new int[N+1][N+1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(i==j) {
						map[i][j]=0;
					}else {
						map[i][j]=INF;
					}
				}
			}
			
			int M=Integer.parseInt(br.readLine()); // 키 비교 횟수
			for (int i = 0; i < M; i++) {
				 //이는 번호가 a인 학생이 번호가 b인 학생보다 키가 작은 것을 의미한다. 이 때, 입력은 항상 모순이 없도록 주어진
				int[] com=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				int a=com[0];
				int b=com[1]; 
				map[a][b]=1;
			}
			
			// map= 각 정점간 거리가 저장되어있는것
			// d = 최단거리를 저장할 배열
			for(int k=1; k<=N; k++){ // k : 거쳐가는 노드 
		        for(int i=1; i<=N; i++){ // i : 출발 노드 
		            for(int j=1; j<=N; j++){ // j : 도착 노드 
		                if(d[i][k] + d[k][j] < d[i][j]){ 
		                    d[i][j] = d[i][k] + d[k][j];
		                }
		            }
		        }
		    }
			
			for (int i = 1; i <=N; i++) {
				for (int j = 1; j <=N; j++) {
					System.out.print(d[i][j]+" ");
				}
				System.out.println();
			}
			
		}
	}
}
