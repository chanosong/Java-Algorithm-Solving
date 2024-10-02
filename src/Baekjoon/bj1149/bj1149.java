package Baekjoon.bj1149;

// https://www.acmicpc.net/problem/1149
// 1149 RGB거리

import java.io.*;
import java.util.*;

public class bj1149 {

    static int n;
    static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        cost = new int[n][3];

        for (int i = 0; i < n; i++) {
            cost[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] dp = new int[n][3];

        // 초기 세팅
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];
        
        for (int i = 1; i < n; i++) {
            dp[i][0] = cost[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = cost[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = cost[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        System.out.println(Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]));
    }
}
