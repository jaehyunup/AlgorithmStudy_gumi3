package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class swea_Ȱ�ַΰǼ� {
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
			if(arr[idx]-arr[idx+1] > 0 ) { // �����Ű� �۾����� (�϶�) idx+1 ���� ���� ������
				int letIdx=idx+1; // ���� ���� �ε���
				int lCount=0; // �������۱���
				while(letIdx>=0 && letIdx < arr.length) { // �ε������϶�
//					System.out.println("���� �۾���Ȯ��"+letIdx);
//					System.out.println("[���� ���ذ� Ȯ�ΰ� ] "+arr[idx]+" "+arr[letIdx]);
					if(Math.abs(arr[idx]-arr[letIdx]) >1 || arr[idx]==arr[letIdx]) { // ���̰� 1�ʰ����
//						System.out.println("���� ���ۺҰ�");
						return 0; // �߰��� ���ܼ� �������۾ȵ�
					}
					
					lCount+=1; // ���� ���۰����� �� 1�� ����
					if(lCount==X) { // ���� ���۰����ϴٸ�
						/*���۰��� */
						isbuild[letIdx]=true; // ���� ����
						break; // �������۷��� ���߱�
					}
					if(isbuild[letIdx]==true) {
						return 0; //���ۺҰ�
					}
					letIdx++;
				}
				
				if(lCount!=X) {
					return 0;
				}
				// �ε����� �Ѿ�ų�, ���� ���۰����ؼ� while ����
				if(letIdx<0 || letIdx >= arr.length) {// �ε����� ����ٸ�
					return 0; // �Ұ����� ����Ǽ�
				}
			}else if(arr[idx]-arr[idx+1] < 0) { // �����Ű� Ŀ���� (���) - idx ���� �ڸ� ������
//				System.out.println("���!!");
				int letIdx=idx; // ���� ���� �ε���
				int lCount=0; // �������۱���
				
				while(letIdx>=0 && letIdx < arr.length) { // �ε������϶�
//					System.out.println("���� Ŀ��Ȯ��"+letIdx);
//					System.out.println("[���� �� �� �İ� ] "+arr[letIdx]+" "+arr[idx+1]);
					if(Math.abs( arr[idx+1] - arr[letIdx]) >1 || arr[idx+1]==arr[letIdx]) { // ���� ���̰� 1�ʰ����
//						System.out.println("���� ���ۺҰ�");
						return 0; // �߰��� ���ܼ� �������۾ȵ�
					}
					if(isbuild[letIdx]==true) {
						return 0; //���ۺҰ�
					}
					lCount+=1; // ���� ���۰����� �� 1�� ����
					if(lCount==X) { // ���� ���۰����ϴٸ�
						isbuild[letIdx]=true; // ����
						break; // �������۷��� ���߱�
					}
					letIdx--;
				}
				if(lCount!=X) {
					return 0;
				}
				// �ε����� �Ѿ�ų�, ���� ���۰����ؼ� while ����
				if(letIdx<0 || letIdx >= arr.length) {// �ε����� ����ٸ�
					return 0; // �Ұ����� ����Ǽ�
				}
			}
			idx+=1;
		}
//		System.out.println("�Ǽ�����!");
		return 1;
		
	}
}