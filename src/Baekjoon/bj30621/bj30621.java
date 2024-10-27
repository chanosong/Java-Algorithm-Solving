package Baekjoon.bj30621;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/30621
// 30621 어? 금지

public class bj30621 {

    static int[] t;
    static int[] b;
    static int[] c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        t = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        c = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[n];

        dp[0] = c[0];

        for (int i = 1; i < n; i++) {
            int temp_t = t[i];
            int temp_b = b[i];
            int temp_c = c[i];

            int past_t = temp_t - temp_b;

            // past_t 보다 작은 가장 큰 수 찾기
            int past_idx = binarySearch(i, past_t);

            // 현재 타임에 외치거나 이전 타임에 외치거나
            if (past_idx >= 0) dp[i] = Math.max(dp[i - 1], dp[past_idx] + temp_c);
            // 쿨타임 걸리지 않는 경우 둘 중 하나
            else dp[i] = Math.max(dp[i - 1], temp_c);
        }

        System.out.println(dp[n - 1]);
    }

    static int binarySearch(int idx, int past_t) {

        int start = 0;
        int end = idx;
        int mid = 0;

        int res = -1;

        while (start < end) {
            mid = (start + end) / 2;

            // 현재 수가 기준보다 작은 경우, 더 큰 수 찾아야함
            if (t[mid] < past_t) {
                res = mid;
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }

        return res;
    }
}
