package Baekjoon.bj1890;

// https://www.acmicpc.net/problem/1890
// 1890 점프

import java.io.*;
import java.util.*;

public class bj1890 {

    static int n;
    static int[][] map;
    static int[][] score;
    static int[] dr = {1,0};
    static int[] dc = {0,1};
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        score = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        score[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j + map[i][j] < n)
                    score[i][j + map[i][j]] += score[i][j];

                if (i + map[i][j] < n)
                    score[i + map[i][j]][j] += score[i][j];
            }

            printMap();
            System.out.println();
        }

        System.out.println(score[n-1][n-1]);
    }

    static void printMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(score[i][j]);
            }
            System.out.println();
        }
    }
}