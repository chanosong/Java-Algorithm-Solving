package Baekjoon.bj2629;

// https://www.acmicpc.net/problem/2629
// 2629 양팔저울

import java.io.*;
import java.util.*;

public class bj2629 {

    static int n;
    static int[] biz;
    static int m;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        biz = new int[n + 1];

        for (int i = 0; i < n; i++) {
            biz[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        dp = new boolean[31][15001];

        recursive(0,0);

        // 가능한 숫자 확인
        for (int i = 0; i < m; i++) {

            int x = Integer.parseInt(st.nextToken());

            if (x > 15000) System.out.print("N ");
            else if (dp[n][x]) {
                System.out.print("Y ");
            }
            else {
                System.out.print("N ");
            }
        }
    }

    static void recursive(int i, int j) {
        if (i > n || dp[i][j]) return;
        dp[i][j] = true;
        recursive(i + 1, j + biz[i]);
        recursive(i + 1,  j);
        recursive(i + 1, Math.abs(j - biz[i]));
    }
}
