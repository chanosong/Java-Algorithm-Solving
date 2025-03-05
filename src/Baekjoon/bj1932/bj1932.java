package Baekjoon.bj1932;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/group/practice/view/20380/52
// 1932 정수 삼각형

public class bj1932 {

    static int n;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int idx = 0;

            while (st.hasMoreTokens()) {
                arr[i][idx] = Integer.parseInt(st.nextToken());
                idx++;
            }
        }

        dp[0][0] = arr[0][0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + arr[i][j]);

                if (j > 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + arr[i][j]);
            }
        }

        System.out.println(Arrays.stream(dp[n - 1]).max().getAsInt());
    }
}
