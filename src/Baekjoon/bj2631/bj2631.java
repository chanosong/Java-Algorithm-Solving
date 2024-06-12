package Baekjoon.bj2631;

// 2631 줄세우기
// https://www.acmicpc.net/problem/2631

import java.io.*;
import java.util.*;

public class bj2631 {

    static int n;
    static int[] arr;

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];

        answer = n;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        recursive(0, 0, 0);

        System.out.println(answer);
    }

    static void recursive(int idx, int max, int cnt) {
        if (idx == n) {
            answer = Math.min(answer, n - cnt);
            return;
        }

        if (arr[idx] > max) {
            recursive(idx + 1, arr[idx], cnt + 1);
        }
        recursive(idx + 1, max, cnt);

    }
}
