import java.util.Arrays;

public class programmers_쿼드압축후개수세기 {
    public static void main(String[] args) {
        programmers_쿼드압축후개수세기 demo = new programmers_쿼드압축후개수세기();
        //System.out.println(demo.solution(new int[]{2, 1, 3, 2},2));
        demo.solution(new int[][]{
                {1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1},{0,1,0,0,1,1,1,1},
                {0,0,0,0,0,0,1,1},{0,0,0,0,0,0,0,1}
                ,{0,0,0,0,1,0,0,1},{0,0,0,0,1,1,1,1}});

    }
    public int[] solution(int[][] arr) {
        int[] answer=new int[]{0,0};
        dfs(arr,0,0, arr.length, answer);

        System.out.println(Arrays.toString(answer));
        return answer;
    }
    public void dfs(int[][] arr,int row,int cols,int step,int[] answer){
        if(step==0){
            answer[arr[row][cols]]+=1;
            return;
        }
        int coreNum=arr[row][cols];
        for (int i = row; i < row+step; i++) {  // 영역내를 검사하고
            for (int j = cols; j < cols+step; j++) {
                if(coreNum!=arr[i][j]){  // 다른 숫자가 발견되면, 4방으로 나누어 다시 압축시도
                    dfs(arr,row,cols,step/2,answer);
                    dfs(arr,row+step/2,cols,step/2,answer);
                    dfs(arr,row,cols+step/2,step/2,answer);
                    dfs(arr,row+step/2,cols+step/2,step/2,answer);
                    return;
                }
            }
        }
        answer[coreNum]+=1;
    }
}
