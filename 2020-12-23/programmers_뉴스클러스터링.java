package jaehyun.com.day1223;

import sun.security.smartcardio.SunPCSC;

import java.util.*;

public class programmers_뉴스클러스터링 {

    public static void main(String[] args) {
        programmers_뉴스클러스터링 demo=new programmers_뉴스클러스터링();
        //demo.solution("FRANCE","french");
        demo.solution("handshake", "shake hands");
        //demo.solution("aa1+aa2","AAAA12");
    }
    public int solution(String str1, String str2) {
        int answer = 0;
        /* 1. 다중집합으로 구성하기 */
        ArrayList<char[]> setA=new ArrayList<char[]>();
        ArrayList<char[]> setB=new ArrayList<char[]>();
        char[] arrA=str1.toLowerCase().toCharArray();
        char[] arrB=str2.toLowerCase().toCharArray();
        makeSet(setA, arrA);
        makeSet(setB, arrB);
        return J(setA,setB);
    }

    public int J(ArrayList<char[]>  setA,ArrayList<char[]>  setB){
        ArrayList<String> inter=new ArrayList<>(); //교
        ArrayList<String> union=new ArrayList<>(); //합
        Map<String,Boolean> visited=new HashMap<>();

        if(setA.size()==0 && setB.size()==0){ // 둘다 공집합이라면
            return 65536;
        }
        Map<String,Integer> hashA=new HashMap<>();
        Map<String,Integer> hashB=new HashMap<>();

        for (char[] chars : setA) {
            String word=String.valueOf(chars);
            if(!hashA.containsKey(word)){
                hashA.put(word,1);
            }else{
                hashA.put(word,hashA.get(word)+1);
            }
        }
        for (char[] chars : setB) {
            String word=String.valueOf(chars);
            if(!hashB.containsKey(word)){
                hashB.put(word,1);
            }else{
                hashB.put(word,hashB.get(word)+1);
            }
        }
        for (String s : hashA.keySet()) {
            if(hashB.containsKey(s)){
                for (int i = 0; i < Math.min(hashA.get(s),hashB.get(s)); i++) {
                    inter.add(s);
                }
            }
        }
        // 합집합 , 두개 다 돌면서 다중인지 아닌지 확인한다.
        //union.addAll(hashA.keySet());
        for (String s : hashA.keySet()) {
            for (int i = 0; i < hashA.get(s); i++) {
                union.add(s);
            }
        }
        for (String s : hashB.keySet()) {
            for (int i = 0; i < hashB.get(s); i++) {
                union.add(s);
            }
        }
        for (String s : inter) {
            union.remove(s);
        }
        if(union.size()==0){
            return 65536;
        }
        float jtemp=(float)inter.size()/(float)union.size();
        jtemp*=65536;
        return (int)jtemp;
    }

    private void makeSet(ArrayList<char[]> setB, char[] arrB) {
        for (int j = 0; j < arrB.length-1; j++) {
            char[] temp=new char[2];
            if('a'<= arrB[j] && arrB[j] <= 'z' && 'a'<= arrB[j+1] && arrB[j+1] <= 'z'){
                // 첫번째 문자가 정상이라면
                temp[0]=arrB[j];
                temp[1]=arrB[j+1];
            }else{
                continue;
            }
            setB.add(temp);
        }
    }


}