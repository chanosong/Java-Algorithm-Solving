package Baekjoon.bj1535;

// 1535 안녕
// https://www.acmicpc.net/problem/1535

import java.util.Arrays;
import java.util.Scanner;

public class bj1535 {
    static int n;
    static int[] health;
    static int[] happy;

    static int nowHealth = 100;
    static int nowHappy = 0;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        // 버퍼 초기화
        sc.nextLine();

        health = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        happy = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(0);

        System.out.println(max);
    }

    static void dfs(int step) {
        // 죽은 경우
        if (nowHealth <= 0) {
            max = Math.max(max, nowHappy - happy[step - 1]);
            return;
        }
        // 다 돌은 경우
        if (step >= n) {
            max = Math.max(max, nowHappy);
            return;
        }
        // 더 가는 경우
        // 인사 안하는 경우
        dfs(step + 1);
        // 인사 하는 경우
        nowHealth -= health[step];
        nowHappy += happy[step];
        dfs(step + 1);
        nowHealth += health[step];
        nowHappy -= happy[step];
    }
}