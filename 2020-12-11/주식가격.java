package io.jaehyunup.github.day1211;

import java.util.Arrays;
import java.util.Stack;

public class 주식가격 {
    public static void main(String[] args) {
        주식가격 demo=new 주식가격();
        System.out.println(Arrays.toString(demo.solution(new int[]{1,2,3,2,3})));
    }
    public int[] solution(int[] prices) {
        int[] answer=new int[prices.length];
        // 스택? 현재 인덱스와, 값을 저장한다.
        Stack<Integer> stack=new Stack<Integer>();
        for (int i = 0; i < prices.length; i++) {
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]){
                // 이전에 지낫던 가격이 현재 가격보다 크다면 ? 가격이 낮아진것임
                // 즉, 다음것과 현재를 비교해서 가격이 낮아짐을 판단하고 낮아지기 전의 answer를 기록함.
                int cursor=stack.pop();
                answer[cursor]=i-cursor;
            }
            stack.push(i); // 이전에 지낫던 시간을 저장한다
        }
        while(!stack.isEmpty()){
            // 남은 stack은 상승이 이어진 구간으로, lenth에서 idx를 빼주어 몇초뒤에 하락하는지 연산한다
            int idx=stack.pop();
            answer[idx]=prices.length-idx-1;
        }
        return answer;
    }
}
