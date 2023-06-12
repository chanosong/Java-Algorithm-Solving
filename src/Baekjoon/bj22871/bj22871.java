package Baekjoon.bj22871;

// 22871 징검다리 건너기 (Large)
// https://www.acmicpc.net/problem/22871

import java.util.Arrays;
import java.util.Scanner;

public class bj22871 {
    static int n;
    static int[] arr;
    static long[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        sc.nextLine();
        arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new long[n];
        dp[1] = Math.abs(arr[1] - arr[0]) + 1L;

        for (int i = 2; i < n; i++) {
            long smallK = Long.MAX_VALUE;

            for (int j = 0; j < i; j++) {
                long tempK = (i - j) * (1L + Math.abs(arr[i] - arr[j]));
                tempK = Math.max(tempK, dp[j]);
                // 최소 K를 갱신한 경우
                if (smallK >= tempK) {
                    smallK = tempK;
                }
            }
            dp[i] = smallK;
        }

        System.out.println(dp[n - 1]);
    }
}
