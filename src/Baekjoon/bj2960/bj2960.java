package Baekjoon.bj2960;

// https://www.acmicpc.net/problem/2960
// 2960 에라토스테네스의 체

import java.io.*;
import java.util.*;

public class bj2960 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
    
        // 2 ~ n에서 채우기
        int[] arr = new int[n + 1];
        int[] cnt = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            arr[i] = i;
        }

        int ord = 0;
        int minNum = 2;

        while (ord < k) {
            // 첫 숫자 찾기

            for (int i = minNum; i <= n; i++) {
                if (cnt[i] == 0) {
                    minNum = arr[i];
                    break;
                }
            }

            // 해당 숫자의 배수 돌리기
            for (int i = minNum; i <= n; i += minNum) {
                // 이미 지워진 숫자라면 스킵
                if (cnt[i] > 0) continue;

                cnt[i] = ++ord;
                if (ord == k) {
                    System.out.println(arr[i]);
                    break;
                }
            }
        }
    }
}
