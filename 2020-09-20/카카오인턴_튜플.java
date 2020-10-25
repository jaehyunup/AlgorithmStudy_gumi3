package com.study.com.day0920;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class 카카오인턴_튜플 {
	public static int[] solution(String s) {
		ArrayList<Integer> answer=new ArrayList<Integer>();
		String[] str = s.split("");
		/* input to array */
		List<ArrayList<String>> tuples=new ArrayList<ArrayList<String>>();
		int i = 1;
		while (i < str.length - 1) { // O(N-2), N= 최대 1,000,000
			if (str[i].equals("{")) { // {를만난다면 한세트시작.
				ArrayList<String> tokenizer = new ArrayList<String>();
				StringBuilder sb = new StringBuilder("");
				int j = i; // j=2
				sb = new StringBuilder(""); // 스트링빌더
				while (!str[++j].equals("}")) { // } 를 만날때까지
					if (str[j].equals(",")) { // , 를 만난다면
						tokenizer.add(sb.toString());
						sb = new StringBuilder("");// 새로운 문자열빌더
					}
					if (str[j].charAt(0) >= 48 && str[j].charAt(0) <= 57) { // 숫자라면
						sb.append(str[j]);
					}
				}
				if(sb.length()>0) {//,이 안나오는경우도 있기때문에 sb빌더 flush해줌
					tokenizer.add(sb.toString());
				}
				tuples.add(tokenizer); // 튜플에 추가
				i = j; // 현재 커서위치에 i옮김
			}
			i++; //다른값이 나왔다면 i커서 증가,다시탐색
		}
		
		// N은 최대 500
		
		Collections.sort(tuples,(o1,o2)->o1.size()-o2.size()); // O(NlogN)
		for (ArrayList<String> tuple : tuples) { // O( N * NlogN)
			for (String num : tuple) {
				int intnum=Integer.parseInt(num);
				if(!answer.contains(intnum)) { // O(N)
					answer.add(intnum);
				}
			}
		}
		return answer.stream().mapToInt(x->x).toArray(); // O(N)
	}
	
	public static void main(String[] args) {
		System.out.println(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"));
//		solution("{{20,111},{111}}");
	}
}
