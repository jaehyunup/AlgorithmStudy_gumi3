package jaehyun.com.day1222;

import java.util.HashSet;
import java.util.Set;

public class programmers_폰켓몬 {

    public static void main(String[] args) {
        programmers_폰켓몬 demo=new programmers_폰켓몬();
        demo.solution(new int[]{3,3,3,2,2,2});
    }
    static int answer;
    public int solution(int[] nums) {
        Set<Integer> set=new HashSet<>();
        int holder=nums.length/2;
        for (int num : nums) {
            set.add(num);
        }
        // set의 사이즈가 작다면
        return Math.min(set.size(), holder);
    }

}