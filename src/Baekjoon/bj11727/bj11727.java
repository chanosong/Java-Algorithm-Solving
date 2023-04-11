package Baekjoon.bj11727;
// https://www.acmicpc.net/problem/11727
import java.util.Scanner;

public class bj11727 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] dp = new int[n];

        // 1 x 2 하나 넣을 경우
        dp[0] = 1;
        // 1 x 2 -> 2개, 2 x 1 -> 2개, 2 x 2 -> 1개
        dp[1] = 3;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + 2 * dp[i - 2];
        }

        System.out.println(dp[n - 1] % 10007);
    }
}
