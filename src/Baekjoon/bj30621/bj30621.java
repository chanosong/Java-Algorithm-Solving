package Baekjoon.bj30621;

import java.io.*;
import java.util.*;

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

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int temp_t = t[i - 1];
            int temp_b = b[i - 1];
            int temp_c = c[i - 1];

            int past_t = temp_t - temp_b;

            // past_t 보다 작은 가장 가까운 수 찾기
            int past_idx = binarySearch(i - 1, past_t);

            if (past_idx >= 0) dp[i] = Math.max(dp[i - 1], dp[past_idx] + temp_c);
            else dp[i] = Math.max(dp[i - 1], temp_c);
        }

        System.out.println(dp[n]);
    }

    static int binarySearch(int idx, int past_t) {

        int start = 0;
        int end = idx;
        int mid = 0;

        while (start <= end) {
            mid = (start + end) / 2;

            if (past_t == t[mid]) return mid;
            else if (past_t < t[mid]) end = mid - 1;
            else start = mid + 1;
        }

        return mid;
    }
}
