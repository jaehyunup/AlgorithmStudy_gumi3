package jaehyun.com.day1228;

import java.util.Arrays;

public class programmers_입국심사 {
    public static void main(String[] args) {
        programmers_입국심사 demo=new programmers_입국심사();
        demo.solution(6,new int[]{7,10});
    }

    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long worst=0; // 심사관이 아무도 검사하지 못했을때의 시간
        long best=times[times.length-1] * (long)n; // 심사관이 모두 검사했을때 가장 오래걸린 시간.
        while(true){
            long mid=(worst+best)/2; // 최악의 시간과 최고의 시간의 사잇값.
            if (worst + 1 == best) return best;
            // mid 분에 검사할 수 있는 사람의 수는
            long person=0;
            for (int i = 0; i < times.length; i++) {
                  person+= mid/times[i];
            }
            if(person>=n){ // n보다 크거나 같다면
                best=mid;// 검사가 가능하다
            }else{ // 검사가 불가능하다.
                worst=mid;
            }
        }
    }
}
