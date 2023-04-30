package Baekjoon.bj2012;
// 2012 등수매기기
// https://www.acmicpc.net/problem/2012
import java.util.Arrays;
import java.util.Scanner;

public class bj2012 {

    static int N;
    static int[] rank;

    static int disstatisfaction = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        N = sc.nextInt();

        rank = new int[N];

        for (int i = 0; i < N; i++) {
            rank[i] = sc.nextInt();
        }

        Arrays.sort(rank);

        long score = 0;

        for (int i = 0; i < rank.length; i++) {
            score += Math.abs((i + 1) - rank[i]);
        }

        System.out.println(score);
    }

    /*
    static void perm(boolean[] used, int n, int r, int score) {
        // 모두 고른 경우
        if (r == 0) {
            // 최소값 갱신
            disstatisfaction = Math.min(disstatisfaction, score);
            return;
        }
        // 더 골라야할 경우
        for (int i = 0; i < n; i++) {
            // 이미 고른 경우 스킵
            if (used[i]) continue;
            // 고르지 않은 경우 고르고 다음 단계
            used[i] = true;
            score += Math.abs(rank[i] - (n - r + 1));
            perm(used, n, r - 1, score);
            score -= Math.abs(rank[i] - (n - r + 1));
            used[i] = false;
        }
    }

     */
}
