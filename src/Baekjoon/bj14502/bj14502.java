package Baekjoon.bj14502;

// https://www.acmicpc.net/problem/14502
// 14502 연구소

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class bj14502 {
    static int n;
    static int m;

    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        // 버퍼 초기화
        sc.nextLine();

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }
}
