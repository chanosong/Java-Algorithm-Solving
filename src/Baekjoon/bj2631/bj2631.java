package Baekjoon.bj2631;

// 2631 줄세우기
// https://www.acmicpc.net/problem/2631

import java.io.*;
import java.util.*;

public class bj2631 {

    static int n;
    static int[] arr;
    static int[] dp;

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];

        answer = n;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[n];

        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        System.out.println(n - Arrays.stream(dp).max().getAsInt());
    }

}
