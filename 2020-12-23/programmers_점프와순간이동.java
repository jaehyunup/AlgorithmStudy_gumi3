package jaehyun.com.day1222;

import java.util.PriorityQueue;

public class programmers_점프와순간이동 {
    public static void main(String[] args) {
        programmers_점프와순간이동 demo=new programmers_점프와순간이동();
        demo.solution(5000);
    }
    public static int answer;
    public int solution(int n) {
        answer = 0;
        go(n,0);
        return answer;
    }

    public void go(int n,int battery){
        if(n==0){
            answer=battery;
            return;
        }
        if(n%2==0){ // 30의 위치를 가장 가까이 가는방법은 15에서 순간이동이다.
            go(n/2,battery);
        }else{
            // 순간이동이 불가능하다면. 1을 빼고 다시 시도해본다.
            go(n-1,battery+1);
        }
    }



}