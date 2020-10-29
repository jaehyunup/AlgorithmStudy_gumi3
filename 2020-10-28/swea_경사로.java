package com.study.day1028;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
����ϴ� Ŭ�������� Solution �̾�� �ϹǷ�, ������ Solution.java �� ����� ���� �����մϴ�.
�̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� �����غ� �� �ֽ��ϴ�.
*/
class swea_경사로 {
	static int N,X;
	static int answer;
	public static void main(String args[]) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] NX=br.readLine().split(" ");
			N=Integer.parseInt(NX[0]);
			X=Integer.parseInt(NX[1]);
			answer=0;
			int[][] map=new int[N][N];
			for (int i = 0; i < N; i++) {
				map[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			Queue<int[]> q=new LinkedList<int[]>();
			for (int i = 0; i < map.length; i++) { 
				q.offer(map[i]); // ������ 
				int[] temp=new int[N];
				for (int j = 0; j < map[i].length; j++) {
					temp[j]=map[j][i]; // ���� ��
				}
				q.offer(temp);
			}
			outer:
			while(!q.isEmpty()) { // ��� ����Ǽ� ��
				int[] curRoad=q.poll();
				boolean[] isLean=new boolean[N];
//				System.out.println(Arrays.toString(curRoad));
				//��˻����
				for (int i = 0; i < curRoad.length-1; i++) {
					int dir=curRoad[i]-curRoad[i+1];
					if(dir==1 &&isLean[i]==false) { // ��簡 �������ٸ�
//						System.out.println("��糷���� : "+i);
						int lengthCount=0;
						int checkLevel=curRoad[i+1];
						int curI=i+1;
						while( ++i < curRoad.length && lengthCount < X) { // �������� �Ѿ�� ����ã��
							if(curRoad[i]==checkLevel) { // ���̰� ���ٸ�
								lengthCount++;
							}
						}
						if(lengthCount==X) { // ���� ���̸�ŭ ��� �̾����ٸ�
							for (int j = curI; j < curI+X; j++) { // ���θ����
								isLean[j]=true;// �����ΰ�? true
							}
						}else {
							continue outer;
						}
						i=curI-1;
					}else {
						continue outer;
					}
				}
				for (int i = curRoad.length-1; i > 1; i--) {
					int dir=curRoad[i-1]-curRoad[i];
					if(dir==-1 && isLean[i]==false) { // ��簡 �������°�
//						System.out.println("�������� : "+i);
						int lengthCount=0;
						int checkLevel=curRoad[i-1];
						int curI=i;
						while( --i >= 0 && lengthCount < X) { // �������� �Ѿ�� ����ã��
							if(curRoad[i]==checkLevel) { // ���̰� ���ٸ�
								lengthCount++;
							}
						}
						if(lengthCount==X) { // ���� ���̸�ŭ ��� �̾����ٸ�
							for (int j = curI-1; j >= curI-X ; j--) { // ���θ����
								isLean[j]=true;// �����ΰ�? true
							}
						}else {
							continue outer;
						}
						
					}else {
						continue outer;
					}
				}
				answer++;
			}
			System.out.println("��"+answer);
		}
	}
}