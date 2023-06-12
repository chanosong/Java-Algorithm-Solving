package Baekjoon.bj15817;

// 15857 배수 공사
// https://www.acmicpc.net/problem/15817

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class bj15857 {
    static int n;
    static int x;
    static int count;
    static int[] length;
    static int[] stock;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        count = 0;

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        x = Integer.parseInt(input[1]);

        dp = new int[n][x + 1];

        length = new int[n];
        stock = new int[n];

        for (int i = 0; i < n; i++) {
            String[] info = br.readLine().split(" ");
            length[i] = Integer.parseInt(info[0]);
            stock[i] = Integer.parseInt(info[1]);
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dp(0,0));
    }

    static int dp (int idx, int nowLength) {
        // 딱 맞춘 경우
        if (nowLength == x) return 1;
        // 목표치를 넘거나 파이프 종류가 더이상 없는 경우
        if (idx >= n || nowLength > x) return 0;

        if (dp[idx][nowLength] != -1) return dp[idx][nowLength];
        dp[idx][nowLength] = 0;

        for (int i = 0; i <= stock[idx]; i++) {
            dp[idx][nowLength] += dp(idx + 1, nowLength + length[idx] * i);
        }

        return dp[idx][nowLength];
    }
}