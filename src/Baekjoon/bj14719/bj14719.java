package Baekjoon.bj14719;

// https://www.acmicpc.net/problem/14719
// 빗물

import java.io.*;
import java.util.*;

public class bj14719 {
    static int h,w;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int answer = 0;

        for (int i = 1; i < w - 1; i++) {
            int left = 0;
            int right = 0;

            for (int j = 0; j < i; j++) {
                left = Math.max(left, arr[j]);
            }

            for (int j = i + 1; j < w; j++) {
                right = Math.max(right, arr[j]);
            }

            // 양쪽에 더 높은 블록이 있는 경우
            if (left > arr[i] && right > arr[i]) {
                answer += Math.min(left, right) - arr[i];
            }
        }

        System.out.println(answer);
    }
}
