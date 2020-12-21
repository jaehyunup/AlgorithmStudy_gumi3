import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class programmers_카펫 {
    public static void main(String[] args) {
        programmers_카펫 demo = new programmers_카펫();
        //System.out.println(demo.solution(new int[]{2, 1, 3, 2},2));
        System.out.println(demo.solution(24,24));

    }
    public int[] solution(int brown, int yellow) {
        int[] answer = {};

        for (int i = 1; i <= yellow; i++) {
            if(yellow%i==0){
                int cols=yellow/i;
                int row=yellow/cols;
                if((2*cols)+(2*row)+4==brown){
                    cols=2+cols;
                    row=2+row;
                    System.out.println(cols+" "+row);
                    return new int[]{cols,row};
                }
            }
        }
        return answer;
    }


}
