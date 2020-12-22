package jaehyun.com.day1222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_RGB마을 {
    static int[][] map;
    static int N;
    static int[][] window;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        map=new int[N][3];
        window=new int[2][3];
        for (int i = 0; i <N; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int idx=0;
            while(st.hasMoreTokens()){
                map[i][idx++]=Integer.parseInt(st.nextToken());
            }
        }
        window[0][0]=map[0][0];
        window[0][1]=map[0][1];
        window[0][2]=map[0][2];
        for (int i = 1; i < map.length; i++) { // 1~N 번째 집의 숫자 결정.
            for (int j = 0; j < 3; j++) { // 괄호 커서. i-1의 윈도우를 보면되지만, windows 0을 최신값으로 한다 그냥..
                int minCost=Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    if(j!=k){
                        minCost=Math.min(minCost,window[0][k]+map[i][j]);
                    }
                }
                window[1][j]=minCost; // window[0][k] + window[1][j]의 최소값
            }
            System.arraycopy(window[1],0,window[0],0,3);
        }
        System.out.println(Arrays.stream(window[0]).min().getAsInt());
    }
}
