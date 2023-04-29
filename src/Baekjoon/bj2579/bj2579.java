package Baekjoon.bj2579;
// https://www.acmicpc.net/problem/2579
// 계단 오르기

import java.util.Scanner;

public class bj2579 {
    static int n;
    static int[] value;
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        value = new int[n + 1];
        dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            value[i] = sc.nextInt();
        }

        dp[1] = value[1];

        if (n > 1) dp[2] = dp[1] + value[2];

        if (n > 2){
            for (int i = 3; i <=n; i++) {
                dp[i] = Math.max(dp[i - 2], value[i - 1] + dp[i - 3]) + value[i];
            }
        }

        System.out.println(dp[n]);
    }
}
