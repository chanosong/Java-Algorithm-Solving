package Baekjoon.bj15989;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/15989
// 15989 1,2,3 더하기 4

public class bj15989 {

    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        int[] dp = new int[10001];

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < t; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        Arrays.fill(dp, 1);

        for (int i = 2; i < 10001; i++) {
            dp[i] += dp[i - 2];
        }

        for (int i = 3; i < 10001; i++) {
            dp[i] += dp[i - 3];
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(dp[list.get(i)]);
        }
    }
}
