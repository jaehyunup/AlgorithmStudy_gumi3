package com.study.day1202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class swea_보물상자비밀번호 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] NK = br.readLine().split(" ");
			int N = Integer.parseInt(NK[0]);
			int K = Integer.parseInt(NK[1]);
			String[] numbers = br.readLine().split("");
			// 각 끝 배열은 비어있다
			int cursor = 0;
			// N/4=> 한변에 들어가야할 숫자 개수
			Set<String> wordList=new HashSet<String>();
			List<Deque<String>> box=new ArrayList<Deque<String>>();
			for (int i = 0; i < 4; i++) { // 1,2,3,4개의 큐가 생성되어야함 
				Deque<String> dq=new LinkedList<String>();
				for (int j = 0; j < N/4; j++) {
					dq.add(numbers[cursor++]);
				}
				box.add(dq);
			}
			
			for (int i = 0; i < N/4; i++) { // N/4회전시 의미 X
				makeWord(wordList,box,N);
				rotate(box,N);
			}
			String[] words=wordList.toArray(new String[N/4]);
			Arrays.sort(words,Comparator.reverseOrder());
			//System.out.println(Arrays.toString(words));
			int answer=Integer.parseInt(words[K-1],16);
			System.out.println("#"+test_case+" "+answer);
			
		}
	}
	public static void makeWord(Set<String> wordList,List<Deque<String>> box,int N) {
		for (int i = 0; i < box.size(); i++) {
			String[] cur=box.get(i).toArray(new String[N/4]);
			StringBuilder sb=new StringBuilder();
			for (int j = 0; j < cur.length; j++) {
				sb.append(cur[j]);
			}
			wordList.add(sb.toString());
		}
	}
	
	public static void rotate(List<Deque<String>> box ,int N) {
		for (int i = box.size()-1; i >-1; i--) {
			if(i==box.size()-1) { // 맨마지막이라면 가장앞으로 push
				box.get(0).addFirst(box.get(i).pollLast());
			}else {
				box.get(i+1).addFirst(box.get(i).pollLast());
			}
		}
		
	}
}
