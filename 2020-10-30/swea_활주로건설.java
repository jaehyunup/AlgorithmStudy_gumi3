package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class swea_활주로건설 {
	static int N,X;
	public static void main(String args[]) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] NX=br.readLine().split(" ");
			N=Integer.parseInt(NX[0]);
			X=Integer.parseInt(NX[1]);
			int[][] map=new int[N][N];
			for (int i = 0; i < N; i++) {
				map[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			int answer=0;
			for (int i = 0; i < map.length; i++) {
				int[] row=map[i];
				int[] cols=new int[N];
				for (int j = 0; j < map.length; j++) {
					cols[j]=map[j][i];
				}
				answer+=check(row,new boolean[row.length]);
				answer+=check(cols,new boolean[cols.length]);
			}
			System.out.println("#"+test_case+" "+answer);
//			System.out.println(check(new int[]{1,1,1,2,2,2,1,1},new boolean[8]));
		}
	}
	public static int check(int[] arr,boolean[] isbuild) {
//		System.out.println(Arrays.toString(arr));
		int idx=0;
		while( idx < arr.length-1 ) {
			if(arr[idx]-arr[idx+1] > 0 ) { // 다음거가 작아질때 (하락) idx+1 포함 앞을 봐야함
				int letIdx=idx+1; // 경사로 시작 인덱스
				int lCount=0; // 경사로제작길이
				while(letIdx>=0 && letIdx < arr.length) { // 인덱스안일때
//					System.out.println("경사로 작아짐확인"+letIdx);
//					System.out.println("[경사로 기준값 확인값 ] "+arr[idx]+" "+arr[letIdx]);
					if(Math.abs(arr[idx]-arr[letIdx]) >1 || arr[idx]==arr[letIdx]) { // 차이가 1초과라면
//						System.out.println("끊김 제작불가");
						return 0; // 중간에 끊겨서 경사로제작안됨
					}
					
					lCount+=1; // 경사로 제작가능한 길 1개 증가
					if(lCount==X) { // 경사로 제작가능하다면
						/*제작가능 */
						isbuild[letIdx]=true; // 제작 저장
						break; // 경사로제작로직 멈추기
					}
					if(isbuild[letIdx]==true) {
						return 0; //제작불가
					}
					letIdx++;
				}
				
				if(lCount!=X) {
					return 0;
				}
				// 인덱스가 넘어가거나, 경사로 제작가능해서 while 종료
				if(letIdx<0 || letIdx >= arr.length) {// 인덱스가 벗어났다면
					return 0; // 불가능한 경우의수
				}
			}else if(arr[idx]-arr[idx+1] < 0) { // 다음거가 커질때 (상승) - idx 기준 뒤를 봐야함
//				System.out.println("상승!!");
				int letIdx=idx; // 경사로 시작 인덱스
				int lCount=0; // 경사로제작길이
				
				while(letIdx>=0 && letIdx < arr.length) { // 인덱스안일때
//					System.out.println("경사로 커짐확인"+letIdx);
//					System.out.println("[경사로 전 값 후값 ] "+arr[letIdx]+" "+arr[idx+1]);
					if(Math.abs( arr[idx+1] - arr[letIdx]) >1 || arr[idx+1]==arr[letIdx]) { // 높이 차이가 1초과라면
//						System.out.println("끊김 제작불가");
						return 0; // 중간에 끊겨서 경사로제작안됨
					}
					if(isbuild[letIdx]==true) {
						return 0; //제작불가
					}
					lCount+=1; // 경사로 제작가능한 길 1개 증가
					if(lCount==X) { // 경사로 제작가능하다면
						isbuild[letIdx]=true; // 제작
						break; // 경사로제작로직 멈추기
					}
					letIdx--;
				}
				if(lCount!=X) {
					return 0;
				}
				// 인덱스가 넘어가거나, 경사로 제작가능해서 while 종료
				if(letIdx<0 || letIdx >= arr.length) {// 인덱스가 벗어났다면
					return 0; // 불가능한 경우의수
				}
			}
			idx+=1;
		}
//		System.out.println("건설성공!");
		return 1;
		
	}
}