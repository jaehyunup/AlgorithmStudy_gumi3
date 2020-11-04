package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class swea_5643_Ű���� {
	public static int[][] map;
	public static int[][] d;
	public static int INF=-1;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N=Integer.parseInt(br.readLine()); // �л���
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
			
			int M=Integer.parseInt(br.readLine()); // Ű �� Ƚ��
			for (int i = 0; i < M; i++) {
				 //�̴� ��ȣ�� a�� �л��� ��ȣ�� b�� �л����� Ű�� ���� ���� �ǹ��Ѵ�. �� ��, �Է��� �׻� ����� ������ �־���
				int[] com=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				int a=com[0];
				int b=com[1]; 
				map[a][b]=1;
			}
			
			// map= �� ������ �Ÿ��� ����Ǿ��ִ°�
			// d = �ִܰŸ��� ������ �迭
			for(int k=1; k<=N; k++){ // k : ���İ��� ��� 
		        for(int i=1; i<=N; i++){ // i : ��� ��� 
		            for(int j=1; j<=N; j++){ // j : ���� ��� 
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
