package Baekjoon.bj25916;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/25916
// 25916 싫은데요

public class bj25916_2 {

    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = 0;
        int end = 0;
        int sum = arr[0];
        int answer = 0;

        while (end < n) {
            if (sum < m) {
                if (end >= n - 1) break;
                sum += arr[++end];
            }
            else if (sum > m){
                sum -= arr[start++];
            }

            if (sum > m) continue;
            answer = Math.max(answer, sum);
            if (sum == m) break;
        }

        System.out.println(answer);
    }
}
