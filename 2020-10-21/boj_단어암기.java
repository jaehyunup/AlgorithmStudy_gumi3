package com.study.day1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_�ܾ�ϱ� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]); // �ܾ��
		int M = Integer.parseInt(NM[1]); // Ŀ�ǵ尳��
		int[] word = new int[N]; 
		int brain= (1 << 27)-1; // 26��° ��Ʈ���� 1�� ��Ʈ mask (ó���� ��� ���ĺ��� �˰��ִ�);
		
		for (int i = 0; i < N; i++) {
			int temp=0; // �ܾ� ��Ʈ
			char[] line = br.readLine().toCharArray();
			for (char c : line) {
				temp = temp |  1 << (c-'a'); 
			}
			word[i]=temp;
			
		}
//		System.out.println(Arrays.toString(word));
		
		/* Ŀ�ǵ�� ���� ���ĺ��� ������� ���ϱ⶧���� �������� �����Ѵ� */
		/* doc�� row�� �� ���� row�� ���Ѵ� */
		
		for (int i = 0; i < M; i++) {
			String com[]=br.readLine().split(" ");
			if(com[0].equals("1")) { // �ؾ�Ա�
				 brain=brain ^ 1 << ( com[1].charAt(0)-'a');
			}else { // ����ϱ�
				 brain= brain | 1 << ( com[1].charAt(0)-'a'); // and������ ���� �ش� ���ĺ� �ڸ��� 1�� �ϱ����� and���� 
			}
			int count=0;
			for (int j = 0; j < N; j++) {
				if(word[j]==(word[j] & brain)) {
					count++;
				}
			}
			System.out.println(count);
		}

	}
}
