package jaehyun.com.day1222;

import java.util.*;

public class programmers_더맵게 {

    public static void main(String[] args) {
        programmers_더맵게 demo=new programmers_더맵게();
        demo.solution(new int[]{0, 0, 0, 0, 0},7);
    }
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq=new PriorityQueue<Integer>(Integer::compareTo);
        for (int num : scoville) {
            pq.add(num);
        }
        while(pq.size()>1 && pq.peek() < K){
            System.out.println(pq.toString());
            int cur=pq.poll();
            int next=pq.poll();
            int mix=cur+(next*2);
            pq.offer(mix);
            answer++;
        }
        if(pq.size()>0 && pq.peek() < K){
            return -1;
        }
        return answer;
    }

}