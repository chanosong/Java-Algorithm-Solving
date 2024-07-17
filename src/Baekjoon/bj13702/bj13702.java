package Baekjoon.bj13702;

// 13702 이상한 술집
// https://www.acmicpc.net/problem/13702

import java.io.*;
import java.util.*;

public class bj13702 {

    static int n;
    static int k;

    static long[] arr;
    static long[] cnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        arr = new long[n];
        cnt = new long[n];

        long sum = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());

            sum += arr[i];
        }

        long mid = sum / k;
        long left = 0;
        long right = Arrays.stream(arr).max().getAsLong();

        while (left < mid) {
            int tmp = 0;
            for (int i = 0; i < n; i++) {
                tmp += arr[i] / mid;
            }

            if (tmp < k) right = mid;
            else left = mid;

            mid = (right + left) / 2;
        }

        System.out.println(mid);
    }
}
