package Baekjoon.bj14002;

// https://www.acmicpc.net/problem/14002
// 가장 긴 증가하는 부분 수열 4

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class bj14002 {
    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n];
        dp = new int[n];

        sc.nextLine();

        arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int tempMax = 0;

            // 이전 수들 중 현재 수보다 작으면서 끌어온 길이가 가장 긴 것으로 치환
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]){
                    tempMax = Math.max(tempMax, dp[j]);
                }
            }

            dp[i] = tempMax + 1;
        }

        Stack<Integer> st = new Stack<>();

        int maxValue = Arrays.stream(dp).max().getAsInt();
        int cursor = maxValue;

        for (int i = n - 1; i >= 0; i--) {
            // 이미 다 뽑은 경우
            if (cursor == 0) break;
            // 만난 경우 커서 줄이고 삽입
            if (dp[i] == cursor) {
                st.push(arr[i]);
                cursor--;
            }
        }

        /*
        for (int i = 0; i < n; i++) {
            System.out.print(dp[i] + " ");
        }
         */

        System.out.println(maxValue);

        while (!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
    }
}
