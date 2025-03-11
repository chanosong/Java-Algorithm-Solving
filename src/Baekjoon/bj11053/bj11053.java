package Baekjoon.bj11053;

// https://www.acmicpc.net/problem/11053
// 가장 긴 증가하는 부분 수열

import java.io.*;
import java.util.*;

public class bj11053 {

    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new int[n];

        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
