package com.study.day1102;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class swea_5644_무선충전 {
	public static int[] drow= {0,-1,0,1,0};
	public static int[] dcols= {0,0,1,0,-1};
	public static int aRow,aCols,bRow,bCols;
	public static int[] commandA;
	public static int[] commandB;
	public static String[][] map;
	public static int[][] charger;
	public static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			/*이동시간 , BC의 개수*/
			String MA[]=br.readLine().split(" ");
			int M=Integer.parseInt(MA[0]);
			int BC=Integer.parseInt(MA[1]);
			answer=0;
			map=new String[11][11];
			commandA=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			commandB=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			charger=new int[BC][4];
			for (int i = 0; i < BC; i++) {
				charger[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			aRow=1;
			aCols=1;
			bRow=10;
			bCols=10;
			/*A =1,1  B= 10,10 */
			answer+=checkCharger();
			for (int time = 0; time < M; time++) {
				// A,B 사용자 위치 업데이트하고
				timeProcess(time);
//				System.out.println("[time]"+time);
				answer+=checkCharger();
			}
			System.out.println("#"+test_case+" "+answer);
		}
	}
	public static void timeProcess(int time) {
		// 매 초마다 사용자의 위치 업데이트
		aRow+=drow[commandA[time]];
		aCols+=dcols[commandA[time]];
		bRow+=drow[commandB[time]];
		bCols+=dcols[commandB[time]];
	}
	
	public static int checkCharger() {
		// A,B 각각 위치한 배터리충전소찾기
		ArrayList<Integer> chargerA=new ArrayList<Integer>();
		ArrayList<Integer> chargerB=new ArrayList<Integer>();
		for (int i = 0; i < charger.length; i++) { // 모든 배터리충전소를 확인하고
			if(charger[i][2]>=Math.abs(aCols-charger[i][0])+Math.abs(aRow-charger[i][1])) {
				// a가 범위안에있다면 
				chargerA.add(i);
			}
		}
		for (int i = 0; i < charger.length; i++) { // 모든 배터리충전소를 확인하고
			if(charger[i][2]>=Math.abs(bCols-charger[i][0])+Math.abs(bRow-charger[i][1])) {
				// b가 범위안에있다면 
				chargerB.add(i);
			}
		}
		//chargerA와 B를 내밀어본다
		return chargeProcess(chargerA,chargerB);
	}
	
	public static int chargeProcess(ArrayList<Integer> chargeA,ArrayList<Integer> chargeB) {
		int max=0,temp=0;
		int aSize=chargeA.size();
		int bSize=chargeB.size();
		if(aSize==0 && bSize==0) return 0; // 둘다 충전할수없을때
		else if(aSize==0) return getMaxPower(chargeB); // B만 충전할수있을떄
		else if(bSize==0) return getMaxPower(chargeA); // A만 충전할 수 있을때
		
		for (Integer a : chargeA) {
			for (Integer b : chargeB) {
				if(a!=b) temp = charger[a][3]+charger[b][3];
				else 	 temp = Math.max(charger[a][3],charger[b][3]);
				if(max <temp) max=temp;
			}
		}
		return max;
	}
	
	public static int getMaxPower(ArrayList<Integer> charge) {
		int max=0;
		for(Integer n:charge) {
			if(max<charger[n][3]) max=charger[n][3];
		}
		return max;
	}
}
