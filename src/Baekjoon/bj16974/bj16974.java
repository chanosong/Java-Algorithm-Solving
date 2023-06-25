package Baekjoon.bj16974;

// https://www.acmicpc.net/problem/16974
// 16974 레벨 햄버거

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj16974 {

    static int n;
    static long x;
    // 패티의 개수
    static long[] patty;
    // 전체 개수
    static long[] len;
    static long answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        x = Long.parseLong(input[1]);

        patty = new long[n + 1];
        patty[0] = 1L;

        len = new long[n + 1];
        len[0] = 1L;

        for (int i = 1; i <= n; i++) {
            patty[i] = patty[i - 1] * 2 + 1;
            len[i] = len[i - 1] * 2 + 3;
        }

        count(n,x);
        System.out.println(answer);
    }

    static void count(int n, long x) {
        if (x == 0) return;
        else if (n == 0) {
            answer++;
            return;
        }
        x--;

        // 온전히 먹지 못하는 경우
        if (x < len[n - 1]) {
            count(n - 1, x);
        }
        // 절반 온전히 먹을 수 있는 경우
        else if (x == len[n - 1]) {
            answer += patty[n - 1];
            count(n - 1, x - len[n - 1]);
        }
        // 절반 보다 더 먹을 수 있는 경우
        else {
            answer += patty[n - 1] + 1;
            count(n - 1, x - len[n - 1] - 1);
        }
    }
}
