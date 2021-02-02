package jaehyun.com.day1228;

public class programmers_자물쇠와열쇠 {
    public static void main(String[] args) {
        programmers_자물쇠와열쇠 demo=new programmers_자물쇠와열쇠();
        demo.solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}},new int[][]{ {1, 1, 1}, {1, 1, 0}, {1, 0, 1} });
    }

    public boolean solution(int[][] key, int[][] lock) {
        int[][] virtualkey=new int[3*lock.length][3*lock.length];

        boolean answer = true;
        return answer;
    }
}
