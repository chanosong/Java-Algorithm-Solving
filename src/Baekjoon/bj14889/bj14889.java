package Baekjoon.bj14889;

// https://www.acmicpc.net/problem/14889
// 스타트와 링크

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class bj14889 {

    static int[][] map;

    static int n;

    // 능력치 차이
    static int gap = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        // 버퍼 초기화
        sc.nextLine();

        // 지도 세팅
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] idxs = new int[n];

        for (int i = 0; i < n; i++) {
            idxs[i] = i;
        }

        comb(0, 0, idxs, new boolean[n]);

        System.out.println(gap);
    }

    static int calc(List<Integer> list, int score) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                score += map[list.get(i)][list.get(j)] + map[list.get(j)][list.get(i)];
            }
        }

        return score;
    }

    static void comb(int start, int depth, int[] arr, boolean[] used) {
        // 이미 다 고른 경우
        if (depth == n / 2) {
            List<Integer> sIdx = new ArrayList<>();
            List<Integer> lIdx = new ArrayList<>();

            for (int i = 0; i < used.length; i++) {
                if (used[i]) sIdx.add(i);
                else lIdx.add(i);
            }

            // 각각 팀 점수 계산
            int sScore = calc(sIdx, 0);
            int lScore = calc(lIdx, 0);

            gap = Math.min(gap, Math.abs(sScore - lScore));

            return;
        }

        // 더 골라야하는 경우
        for (int i = start; i < arr.length; i++) {
            // 이미 사용한 경우 스킵
            if (used[i]) continue;

            used[i] = true;
            comb(i + 1, depth + 1, arr, used);
            used[i] = false;
        }
    }
}