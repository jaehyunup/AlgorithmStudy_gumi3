package jaehyun.com.day1222;

import java.util.*;

public class programmers_JADENCASE문자열 {

    public static void main(String[] args) {
        programmers_JADENCASE문자열 demo=new programmers_JADENCASE문자열();
        demo.solution("3people  unFollowed me");
    }
    static int answer;
    public String solution(String s) {
        StringBuilder sb=new StringBuilder();
        StringTokenizer st=new StringTokenizer(s," ",true);
        while(st.hasMoreTokens()){
            String tokenString=st.nextToken().toLowerCase();
            char[] tokenArr=tokenString.toCharArray();

            if('a' <= tokenArr[0] && tokenArr[0]<= 'z'){
                tokenArr[0]=Character.toUpperCase(tokenArr[0]);
            }
            sb.append(String.valueOf(tokenArr));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

}