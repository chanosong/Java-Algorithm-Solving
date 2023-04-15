package Programmers.Tiling3xN;
// https://school.programmers.co.kr/learn/courses/30/lessons/12902
public class Tiling3xN {
    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    static int solution (int n){
        int answer = 0;

        if (n % 2 == 0) return 0;

        int[] dp = new int[n / 2];
        dp[0] = 0;
        dp[1] = 3;
        dp[2] = 11;

        for (int i = n / 2; i < n; i++) {
            dp[i] = dp[i - 1] * 3;
            //for (int j = 1);
        }

        answer = dp[n - 1];
        return answer;
    }
}
