import java.util.*;

public class programmers_가장큰수 {
    public static void main(String[] args) {
        programmers_가장큰수 demo = new programmers_가장큰수();
        System.out.println(demo.solution(new int[]{1000,1000,999,344,333}));
    }
    public String solution(int[] numbers) {
        ArrayList<Integer> numberList=new ArrayList<>();
        for (int number : numbers) {
            numberList.add(number);
        }
        numberList.sort((o1, o2) -> {
            int o1S = Integer.parseInt(o1.toString() + o2.toString());
            int o2S = Integer.parseInt(o2.toString() + o1.toString());
            return o2S-o1S;
        });
        StringBuilder sb=new StringBuilder();
        if(numberList.get(0)==0){
            return "0";
        }
        for (Integer integer : numberList) {
            sb.append(integer);
        }
        return sb.toString();
    }

}
