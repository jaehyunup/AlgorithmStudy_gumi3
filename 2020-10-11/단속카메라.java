import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes, (arr1,arr2) -> {return Integer.compare(arr1[0],arr2[0]);});
        int lastOut=routes[0][1];
        for(int[] route:routes){
            if(lastOut > route[1]){ // 전 진출보다 작다면 포함할수있음
                lastOut=route[1];
            }else if(lastOut < route[0]){ // 새로운 포함관계 형성
                lastOut=route[1];
                answer++;
            }       
        }
        return answer;
        
    }
}