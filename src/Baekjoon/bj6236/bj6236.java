package Baekjoon.bj6236;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/6236
// 용돈 관리

public class bj6236 {

    static int n, m, answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 이분 탐색
        int left = Arrays.stream(arr).max().getAsInt();
        int right = Arrays.stream(arr).sum();

        while (left <= right) {
            int mid = (right + left) / 2;

            if (check(mid, arr) <= m) {
                answer = mid;
                right = mid - 1;
            }
            else left = mid + 1;
        }

        System.out.println(answer);
    }

    static int check(int money, int[] arr) {
        int cnt = 1;
        int balance = money;

        for (int a : arr) {
            balance -= a;

            if (balance < 0) {
                cnt++;
                balance = money - a;
            }
        }

        return cnt;
    }
}
