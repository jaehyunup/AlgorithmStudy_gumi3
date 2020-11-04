package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class boj_15685_드래곤커브 {
	public static int[] drow = { 0, -1, 0, 1 };
	public static int[] dcols = { 1, 0, -1, 0 };
	public static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[101][101];
		for (int i = 0; i < N; i++) {
			int[] dragon = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Stack<Integer> startStack = new Stack<Integer>();
			startStack.push(dragon[2]);
			makeDragon(dragon[1], dragon[0], dragon[3], 0, startStack);
		}
		System.out.println(checkBlock());

	}

	public static int checkBlock() {
		int answer = 0;
		/*0,0 0,1 1,0 1,1  */
		for (int i = 0; i < map.length-1; i ++) { // 0~2,4,6,8 ~99
			for (int j = 0; j < map[i].length-1; j++) { // 0~2,4,6,899 
				if(map[i][j]>0 && map[i+1][j]>0 && map[i][j+1]>0 && map[i+1][j+1]>0) {
					answer++;
				}
			}

		}
		return answer;
	}

	public static void makeDragon(int row, int cols, int generation, int count, Stack<Integer> lastStack) {
		if (count == generation) { // 모든 세대가 만들어졌을때
//			System.out.println(lastStack.toString());
			Stack<Integer> finalStack = new Stack<Integer>();
			while (!lastStack.isEmpty()) {
				finalStack.push(lastStack.pop());
			}
			map[row][cols] += 1;
			while (!finalStack.isEmpty()) {
				int direction = finalStack.pop();
//				System.out.println(direction);
				row += drow[direction];
				cols += dcols[direction];
				map[row][cols] += 1;
			}
			int answer = 0;

			return;
		}
		Stack<Integer> temp = new Stack<Integer>();
		Queue<Integer> rq = new LinkedList<Integer>();
		Stack<Integer> ns = new Stack<Integer>();
		/*
		 * next stack은 전에꺼부터 다담아야함. commandList는 항상 추가되어야하고 전에서 전달된 list에 현재 변경되어 적용된
		 * 값까지 모두 담는다.
		 */
		while (!lastStack.isEmpty()) {
			int com = lastStack.pop();
			temp.push(com);
			rq.offer(com);
			// 드래곤커브 찍기
		}

		while (!temp.isEmpty()) {
			ns.push(temp.pop());
		}
		while (!rq.isEmpty()) {
			ns.push(lotateDirection(rq.poll()));
		}
		makeDragon(row, cols, generation, count + 1, ns);
		return;
	}

	public static int lotateDirection(int num) {
		if (num == 3) {
			return 0;
		} else {
			return num + 1;
		}
	}

}
