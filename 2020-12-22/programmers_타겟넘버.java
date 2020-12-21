import java.util.*;

public class programmers_타겟넘버 {
    static int answer;
    public static void main(String[] args) {
        programmers_타겟넘버 demo = new programmers_타겟넘버();
        //System.out.println(demo.solution(new int[]{2, 1, 3, 2},2));
        System.out.println(demo.solution(new int[]{2, 3, 5, 7,9},2));

    }

    public int solution(int[] numbers, int target) {
        answer = 0;
        for (int i = 0; i <= numbers.length; i++) {
            go(numbers,new boolean[numbers.length],0,0,i,target);
        }
        return answer;
    }
    public void go(int[] numbers,boolean[] visited,int depth,int count,int n,int targetNumber){
        if(count==n){
            int num=0;
            for (int i = 0; i < visited.length; i++) {
               if(visited[i]){
                   num+=numbers[i];
               }else{
                   num-=numbers[i];
                }
            }
            if(num==targetNumber){
                answer++;
            }
            return;
        }

        if(depth>=numbers.length){
            return;
        }
        visited[depth] = true;
        go(numbers, visited, depth + 1,count+1,n, targetNumber);
        visited[depth] = false;
        go(numbers, visited, depth + 1, count,n,targetNumber);
    }

}
