package Baekjoon.bj28018;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/28018
// 시간이 겹칠까?

public class bj28018 {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[1000002];

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr[s]++;
            arr[e + 1]--;
        }

        int[] sumArr = new int[1000002];
        int cnt = 0;

        for (int i = 1; i < 1000001 ; i++) {
            cnt += arr[i];
            sumArr[i] = cnt;
        }

        int q = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < q; i++) {
            int x = Integer.parseInt(st.nextToken());

            System.out.println(sumArr[x]);
        }
    }
}
