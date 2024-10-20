package Baekjoon.bj29717;

// https://www.acmicpc.net/problem/29717
// 29717 슬라임 잡고 레벨 업!

import java.util.*;
import java.io.*;

public class bj29717 {

    static int t;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        int[] arr = new int[t];
        long[] answer = new long[t];

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[i] = n;

            long exp = (long) n * (n + 1) / 2;

            long left = 0;
            long right = (long) Math.pow(10,9);

            while (left <= right) {
                long mid = (right + left) / 2;

                if (mid * (mid + 1) <= exp) left = mid + 1;
                else right = mid - 1;
            }

            answer[i] = left;
        }


        for (int i = 0; i < t; i++) {
            System.out.println(answer[i]);
        }
    }
}
