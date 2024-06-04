package Baekjoon.bj2493;

// https://www.acmicpc.net/problem/2493
// 2493 탑

import java.io.*;
import java.util.*;

public class bj2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] height = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] answer = new int[n];

        int maxHeight = height[0];
        int minHeight = 0;
        int maxIdx = 0;
        int minIdx = 0;

        for (int i = 1; i < n; i++) {
            if (height[i] >= maxHeight) {
                maxHeight = height[i];
                maxIdx = i;
            }
            else if (height[i] <= minHeight) {
                minHeight = height[i];
                answer[i] = minIdx + 1;
                minIdx = i;
            }
            else {
                minHeight = height[i];
                answer[i] = maxIdx + 1;
                minIdx = i;
            }
        }

        for (int h : answer) {
            System.out.print(h + " ");
        }
        System.out.println();
    }
}
