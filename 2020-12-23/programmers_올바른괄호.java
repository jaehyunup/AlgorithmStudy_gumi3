package jaehyun.com.day1222;

import java.util.*;

public class programmers_올바른괄호 {
    public static void main(String[] args) {
        programmers_올바른괄호 demo=new programmers_올바른괄호();
        System.out.println(demo.solution("(()("));
    }

    public boolean solution(String s) {
        Stack<Integer> open=new Stack<>();
        char[] arr=s.toCharArray();
        for (char c : arr) {
            if (c == '(') {
                open.push(0);
            } else { // close 일때
                if (open.size() == 0) {
                    return false;
                } else {
                    open.pop();
                }
            }
        }
        return open.isEmpty();
    }


}