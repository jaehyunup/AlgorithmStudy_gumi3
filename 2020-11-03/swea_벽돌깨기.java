package com.study.day1103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class swea_벽돌깨기 {
	static int N,W,H;
	static boolean[][] visited;
	static int[][] map;
	static int[] drow= {-1,1,0,0};
	static int[] dcols= {0,0,-1,1};
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int NWH[]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			N=NWH[0];
			W=NWH[1];
			H=NWH[2];
			
			map=new int[H][W];
			visited=new boolean[H][W];
			answer=Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				map[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			int[] numbers=new int[W];
			int[] result=new int[N];
			for (int i = 0; i < W; i++) {
				numbers[i]=i;
			}
			combi(0,0,numbers,result);
			
			System.out.println("#"+test_case+" "+(answer));
		}
	}
	public static void combi(int idx,int count,int[] numbers,int[] result) {
		if(count==N) { // 깰 벽돌조합을 찾고 깨보기
			// 맵생성
			int[][] tempMap=copy(map);
//			System.out.println(Arrays.toString(result));
			for (int i = 0; i < result.length; i++) { // 구슬 떨어트리기 
				dropBall(tempMap,result[i]);
				tempMap=downBrick(tempMap);
//				for (int j = 0; j < tempMap.length; j++) {
//					for (int j2 = 0; j2 < tempMap[j].length; j2++) {
//						System.out.print(tempMap[j][j2]+" ");
//					}
//					System.out.println();
//				}
//				System.out.println("=======");
			}
			int sum=0;
			for (int r = 0; r < tempMap.length; r++) {
				for (int c = 0; c < tempMap[r].length; c++) {
					if(tempMap[r][c]!=0) {
						sum++;
					}
				}
			}
			answer=Math.min(sum,answer);
			return;
		}
		if(idx >= W) { // 인덱스가 범위를 벗어난다면
			return;
		}
		for (int i = 0; i < W; i++) { // idx 부터 시작해서
				result[count]=numbers[i];
				combi(idx,count+1,numbers,result);
		}
	}
	public static int dropBall(int[][] tempMap,int idx) {
		/*idx에 구슬을 떨어트린다*/
		for (int j = 0; j < H; j++) {
			if(tempMap[j][idx]!=0) {
//				System.out.println(j);
				breakBrick(tempMap,j,idx);
				break;
			}
		}
		return 0;
	}
	/*벽돌 내리기*/
	public static int[][] downBrick(int[][] tempMap) {
		int[][] retMap=new int[H][W];
		for (int i = 0; i < W; i++) { // 옆으로 긁으면서
			int rowIdx=H-1;
			for (int j = H-1; j >=0; j--) {
				if(tempMap[j][i]!=0) { // 0이 아닌것을 찾았다면
					retMap[rowIdx--][i]=tempMap[j][i];
				}
			}
		}
		return retMap;
	}
	public static void breakBrick(int[][] tempMap,int row,int cols) { // 해당 idx의 가장 윗값의 벽돌이 반응하여 연쇄반응을 일으킨다
		int origin=tempMap[row][cols];
		tempMap[row][cols]=0;
		if(origin<=1) { // 1일때는 자기자신만 제거된다.
			return;
		}else {
			for (int step = 1; step < origin; step++) { // power만큼 모두 제거
				for (int i = 0; i < drow.length; i++) {
					int nrow=row+(drow[i]*step);
					int ncols=cols+(dcols[i]*step);
					if(nrow<0 || ncols<0 || nrow>=H || ncols>=W ) { 
						continue;
					}
					if(origin!=0) {
						breakBrick(tempMap,nrow,ncols); // 연쇄반응
					}
				}
			}
		}
		return;
		
	}
	
	public static int[][] copy(int[][] map){
		int[][] retMap=new int[H][W];
		for (int i = 0; i < H; i++) {
			retMap[i]=Arrays.copyOf(map[i],map[i].length);
		}
		
		return retMap; 
	}
}
