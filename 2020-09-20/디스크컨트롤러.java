package com.study.com.day0920;
import java.util.*;
public class 디스크컨트롤러 {
	public static int solution(int[][] jobs) {
        /*가장빠른 요청부터 정렬*/
        int answer=0;
        Arrays.sort(jobs,(s1,s2)->s1[0]-s2[0]);
        /*우선순위 큐를 통해서 소요시간이 가장 작은 순서대로 큐에 유지*/
        PriorityQueue<int[]> q=new PriorityQueue<int[]>(new Comparator<int[]>(){
              public int compare(int[] w1, int[] w2) {                         
                    return w1[1]-w2[1];   
                }      
        });
        int idx=0;
        int time=0;
        while(idx < jobs.length || !q.isEmpty()){ // job의 개수만큼
            while( idx<jobs.length&&time >= jobs[idx][0]){ // 현재 처리할수있는 작업의 리스트
                q.offer(jobs[idx++]); // 큐에 넣어서 작업리스트중 소요시간이 가장작은 순서대로
            }
            if(q.isEmpty()){ // 큐가 비어있다면 현재시간의 갱신이 필요(작업이연결되지않을때)
                time=jobs[idx][0];
            }else{ // 큐가 비어있지않다면
                int[] temp=q.poll();
                // 요청에서 종료까지 시간 평균구하기
                answer+=time-temp[0]+temp[1];
                // 대기시간(현재시간-요청시간)+수행시간
                time+=temp[1]; // 작업이 끝나고 현재시간을 기록        
            }       
         }
        /*첫작업 뒤의 작업은 작업시간이 작은순으로 실행되어야한다.*/
        /*현재시간보다, 요청된 작업중 시작시간이 같거나 작은 작업들은 
        	실행할 수 있는 작업이고, 그중 소요시간이 가장 작은것부터 수행해나간다.*/
        answer/=jobs.length;
        return answer;
    }
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0,3},{1,9},{2,6}} ));
	}
}
