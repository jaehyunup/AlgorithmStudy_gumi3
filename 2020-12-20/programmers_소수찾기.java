import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class programmers_소수찾기 {
    public static int answer;
    public static Map<Integer, Boolean> hash;
    public static int[] primeNumbers;
    public static void main(String[] args) {
        programmers_소수찾기 demo = new programmers_소수찾기();
        demo.solution("17");

    }

    public int solution(String numbers) {
        answer = 0;
        primeNumbers=makePrimeNumber(9999999);
        hash = new HashMap<Integer, Boolean>();
        hash.put(0,true);
        hash.put(1,true);
        String[] numberArr = numbers.split("");
        for (int i = 1; i <= numberArr.length; i++) {
            perm(numberArr, new String[i], new boolean[numberArr.length], i, 0);
        }
        System.out.println(answer);
        return answer;
    }

    public void perm(String[] numberArr, String[] result, boolean[] visited, int r, int depth) {
        if (depth == r) {
            StringBuilder sb = new StringBuilder();
            for (String s : result) {
                sb.append(s);
            }
            int checkNum=Integer.parseInt(sb.toString());
            if (isPrimeNumber(checkNum)) {
                hash.put(checkNum,true);
                answer++;
            }
            ;
            return;
        }
        for (int i = 0; i < numberArr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = numberArr[i];
                perm(numberArr, result, visited, r, depth + 1);
                visited[i] = false;
            }
        }
    }

    public int[] makePrimeNumber(int n) {
        int[] primeArr = new int[n + 1];
        for (int i = 0; i < primeArr.length; i++) {
            primeArr[i] = i;
        }
        for (int i = 2; i <= n; i++) {
            if (primeArr[i] == 0) continue;
            for (int j = i + i; j <= n; j += i) {
                primeArr[j] = 0;
            }
        }
        return primeArr;
    }

    public boolean isPrimeNumber(int number){
        return primeNumbers[number] != 0 && !hash.containsKey(number);
    }
}
