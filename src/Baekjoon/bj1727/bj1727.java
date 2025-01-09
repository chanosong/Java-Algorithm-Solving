package Baekjoon.bj1727;

// https://www.acmicpc.net/problem/1727
// 1727 커플 만들기

import java.io.*;
import java.util.*;

public class bj1727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer("");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] male = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] female = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(male);
        Arrays.sort(female);

        int[][] dp = new int[n][n];
    }
}
