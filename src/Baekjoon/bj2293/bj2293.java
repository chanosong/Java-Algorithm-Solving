package Baekjoon.bj2293;

// https://www.acmicpc.net/problem/2293

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class bj2293 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] cmd = br.readLine().split(" ");
        int n = Integer.parseInt(cmd[0]);
        int k = Integer.parseInt(cmd[1]);

        Integer[] values = new Integer[n];

        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }


        // int answer = dp(0,k,values);
        int answer = 0;

        // Bottom Down
        int[] dp2 = new int[k + 1];
        // 베이스
        dp2[0] = 1;

        for(int i = 0; i < n; i++) {
            for (int j = values[i]; j <= k; j++) {
                dp2[j] += dp2[j - values[i]];
            }
        }
        answer = dp2[k];
        System.out.println(answer);
    }


    // values[cur] 동전을 갖고 remain 채울 수 있는 경우의 수 Top Down
    static int dp(int cur, int remain, Integer[] values) {

        // 정확하게 맞춘 경우
        if (remain == 0) return 1;

        // 모든 종류를 사용하였는데 채울 금액이 남은 경우
        if(cur >= values.length) return 0;

        int cnt = remain / values[cur];
        int sum = 0;

        for (int i = 0; i <= cnt; i++) {
            sum += dp(cur + 1, remain - values[cur] * i, values);
        }

        return sum;
    }
}
