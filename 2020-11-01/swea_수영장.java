package com.study.day1031;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class swea_수영장 {
	/* 1일,1달,3달,12달 이용권 가격 */
	public static int[] ticket;
	public static int answer = Integer.MAX_VALUE;
	public static int depth = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			ticket = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] plan = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			answer=ticket[3];
			checkMoney(0, plan, 0);
			System.out.println("#"+test_case+" "+answer);
		}
	}
	public static void checkMoney(int monthIdx, int[] plan, int money) {
		if (monthIdx >= 12) { // 1년이 지난다면 연산끝
			answer = Math.min(money, answer);
			return;
		}
		if (plan[monthIdx] == 0) { // 수영장 이용 안하는달이라면 걍 넘어감
			checkMoney(monthIdx + 1, plan, money);
		} else {
			// 1일
			checkMoney(monthIdx + 1, plan, money + plan[monthIdx] * ticket[0]);
			// 1달
			checkMoney(monthIdx + 1, plan, money + ticket[1]);
			// 3달
			checkMoney(monthIdx + 3, plan, money + ticket[2]);
		}
	}
}