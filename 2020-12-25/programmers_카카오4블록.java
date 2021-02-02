package jaehyun.com.day1225;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class programmers_카카오4블록 {

    public static void main(String[] args) {
        programmers_카카오4블록 demo = new programmers_카카오4블록();
        //demo.solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"});
        demo.solution(8,2,new String[]{"FF", "AA", "CC", "AA", "AA", "CC", "DD", "FF"});
    }

    public static char[][] map;
    public static int M, N;
    public static int[] drow = {1, 0, 1};
    public static int[] dcols = {0, 1, 1};

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        M = m;
        N = n;
        map = new char[M][N];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        while (true) {
            int score=0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] >= 'A' && map[i][j] <= 'Z') {
                        score+=go(i,j,map[i][j]);
                    }
                }
            }
            if(score==0){
                System.out.println(answer);
                return answer;
            }
            // 2 . 소문자인 블럭을 모두 삭제시키고 위의 블럭들을 다 땡겨 온다.
            for (int i = 0; i < N; i++) { // 가로 모든행 탐색
                for (int j = M - 1; j >= 0; j--) { // 세로 아래행부터 쭉 떙기기
                    if (map[j][i] == '0' || ('a' <= map[j][i] && map[j][i] <= 'z')) {
                        // 소문자거나 0이라면, 위에 삭제할 애를 찾는다.
                        // 대문자를 찾는다
                        int idx = j - 1;
                        while (idx >= 0) {
                            if (map[idx][i] >= 'A' && map[idx][i] <= 'Z') {
                                break;
                            }
                            map[idx][i] = '0';
                            idx--;
                        }
                        if (idx != -1) {
                            map[j][i] = map[idx][i];
                            map[idx][i] = '0';
                        }
                    }
                }
            }

//            for (int i = 0; i < map.length; i++) {
//                for (int j = 0; j < map[i].length; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
            answer+=score;
        }
    }

    public int go(int row, int cols, char type) {
        int count = 0;
        for (int i = 0; i < drow.length; i++) {
            int nrow = row + drow[i];
            int ncols = cols + dcols[i];
            if (nrow < 0 || ncols < 0 || nrow >= M || ncols >= N || map[nrow][ncols] != type) { // 블럭내에 하나라도 틀린게있다면
                return 0;
            }
        }
        for (int i = 0; i < drow.length; i++) { // 카운트 연산 하는 부분
            int nrow = row + drow[i];
            int ncols = cols + dcols[i];
            if ('A' <= map[nrow][ncols] && map[nrow][ncols] <= 'Z') { // 대문자라면 카운트를 올리고 소문자로 변경한다.
                map[nrow][ncols] = Character.toLowerCase(map[nrow][ncols]);
                count++;
            }
            count += go(nrow, ncols, type); // 카운트 하고 난 이후, 각각의 연산을 별개로 실행한다. 이떄 카운트에 더한다 .
        }
        if (map[row][cols] == type) {
            map[row][cols] = Character.toLowerCase(map[row][cols]);
            return ++count;
        }

        return count;
    }

}