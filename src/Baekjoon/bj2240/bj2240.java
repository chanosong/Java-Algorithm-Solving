package Baekjoon.bj2240;

// https://www.acmicpc.net/problem/2240
// 2240 자두나무

import java.io.*;
import java.util.*;

public class bj2240 {

    static int t;
    static int w;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        dp = new int[t][w + 1];

        int[] plum = new int[t];

        for (int i = 0; i < t; i++) {
            plum[i] = Integer.parseInt(br.readLine());
        }

        dp[0][0] = (plum[0] == 1) ? 1 : 0;
        dp[0][1] = (plum[0] == 2) ? 1 : 0;

        for (int i = 1; i < t; i++) {
            for (int j = 0; j < w + 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j] , dp[i - 1][Math.max(j - 1, 0)]);
                
                // 2번 나무에서 떨어지는 경우
                if (plum[i] % 2 == 0) {
                    // 홀수번 움직인 경우 카운트 증가
                    dp[i][j] += ((j + 1) % 2 == 0) ? 1 : 0;
                }
                else {
                    // 짝수번 움직인 경우 카운트 증가
                    dp[i][j] += ((j + 1) % 2 == 1) ? 1 : 0;
                }
            }
        }

        System.out.println(Arrays.stream(dp[t - 1]).max().getAsInt());
    }
}
