import java.util.*;

public class programmers_프린터 {
    public static void main(String[] args) {
        programmers_프린터 demo = new programmers_프린터();
        //System.out.println(demo.solution(new int[]{2, 1, 3, 2},2));
        System.out.println(demo.solution(new int[]{1, 1, 9, 1,1,1},0));

    }
    public int solution(int[] priorities, int location) {
        Queue<int[]> q=new LinkedList<int[]>();
        PriorityQueue<Integer> hash=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int i = 0; i < priorities.length; i++) {
            int priority = priorities[i];
            q.offer(new int[]{priority,i}); // [우선순위, 번호]
            hash.offer(priority);
        }
        int count=0;
        Queue<int[]> wait=new LinkedList<>();

        while(!q.isEmpty()){
            int[] cur =q.poll();
            if(cur[0]==hash.peek()){
                    hash.poll();
                    // 뽑을수 있을때는 지금까지 대기열을 뒤에 붙힌다.
                    ++count; // 뽑고
                    if(cur[1]==location){ // 뽑고싶은애라면 리턴
                        return count;
                    }
                    while(!wait.isEmpty()){
                        q.offer(wait.poll());
                    }
                    continue;
                }else{
                    // 뽑을 수 없을때는 저장한다.
                    wait.offer(cur);
                }
        }
        return -1;
    }

}
