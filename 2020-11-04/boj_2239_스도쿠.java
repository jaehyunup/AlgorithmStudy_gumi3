package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class boj_스도쿠 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[9][9];
		for (int i = 0; i < 9; i++) {
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		check(map, 0);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
	}

	public static boolean check(int[][] map, int index) {
		/*행 = /9 , 열= %9 */
		int row=index/9;
		int cols=index%9;
		boolean isfinal;
		
		if(index==81) {
			return true;
		}
		if (map[row][cols] == 0) {
			outerCon:
			for (int number = 1; number <= 9; number++) { // 모든 숫자를 사전순으로 대입
				// 열체크
				for (int c = 0; c < 9; c++) {
					if(map[row][c]==number) {
						continue outerCon;
					}
				}
				//행체크
				for (int r = 0; r < 9 ; r++) {
					if(map[r][cols]==number) {
						continue outerCon;
					}
				}
				// 범위체크
				if(!step3(map,row,cols,number)) {
					continue outerCon;
				}
				map[row][cols]=number;
				
				if(check(map,index+1) || index==80) {
					return true;
				}; // 81불렀어
				map[row][cols]=0;
				
			}
		}
		else {
			return check(map,index+1);
		}
		return false;
	}

	public static boolean step3(int[][] map, int row, int cols, int number) {
		Map<Integer, Integer> hash = new HashMap<>();
		int brow=row/3;
		int bcols=cols/3;
		
		for (int j = brow*3; j < (brow*3)+3; j++) {
			for (int x = bcols*3; x < (bcols*3)+3; x++) {
//				System.out.println(" row: "+j+" cols: "+x);
				if (hash.containsKey(number)) {
					return false;
				}
				hash.put(map[j][x], map[j][x]);
			}
		}
		return true;
	}

	public static boolean isClosed(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

}
