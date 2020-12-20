import java.util.Arrays;

public class programmers_삼각달팽이 {
    public static int[] drow={1, 0,-1};
    public static int[] dcols={0, 1,-1};
    public static void main(String[] args) {
        programmers_삼각달팽이 demo=new programmers_삼각달팽이();
        System.out.println(Arrays.toString(demo.solution(5)));
    }
    // 1 0 0 0 0 0
    // 2 12 0 0 0 0
    // 3 0 11 0 0 0
    // 4 0 0 10 0 0
    // 5 6 7 8 9 0
    public int[] solution(int n) {
        int maxNum=n*(n+1)/2;
        int[] answer = new int[maxNum];
        int[][] map=new int[n][n];
        int count=1;
        int row=-1,cols=0;
        int idx=0;
        while(true) {
            int direction=0;
            for (int i = n; i > n - 3; i--) {
                for (int j = 0; j < i; j++) {
                    row += drow[direction];
                    cols += dcols[direction];
                    map[row][cols] = count++;
                    if(count > maxNum){
                        for (int[] ints : map) {
                            for (int anInt : ints) {
                               if(0!=anInt){
                                   answer[idx++]=anInt;
                               }
                            }
                        }
                        return answer;
                    }
                }
                direction++;
            }
            n-=3;
        }
    }

}
