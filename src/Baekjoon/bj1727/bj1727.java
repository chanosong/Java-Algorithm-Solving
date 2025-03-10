package Baekjoon.bj1727;

// https://www.acmicpc.net/problem/1727
// 1727 커플 만들기

import java.io.*;
import java.util.*;

public class bj1727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] male = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] female = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(male);
        Arrays.sort(female);

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == j) dp[i][j] = dp[i - 1][j - 1] + Math.abs(male[i - 1] - female[j - 1]);
                else if (i > j) dp[i][j] = Math.min(dp[i - 1][j - 1] + Math.abs(male[i - 1] - female[j - 1]), dp[i - 1][j]);
                else dp[i][j] = Math.min(dp[i - 1][j - 1] + Math.abs(male[i - 1] - female[j - 1]), dp[i][j - 1]);
            }
        }

        System.out.println(dp[n][m]);
    }
}
