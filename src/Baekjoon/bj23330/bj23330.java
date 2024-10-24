package Baekjoon.bj23330;

import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/23330
// 거리의 합 2

public class bj23330 {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        long[] sum = new long[n];

        sum[0] = arr[0];

        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        long answer = 0;

        for (int i = n - 1; i > 0; i--) {
            answer += (long) arr[i] * i - sum[i - 1];
        }

        System.out.println(answer * 2);
    }
}
