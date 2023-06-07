package Baekjoon.bj2075;

// https://www.acmicpc.net/problem/2075
// N번째 큰 수

import java.util.Arrays;
import java.util.Scanner;

public class bj2075 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 버퍼 초기화
        sc.nextLine();

        int[][] numbers = new int[n][n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] idxs = new int[n];
        Arrays.fill(idxs,n - 1);

        int cnt = 0;
        int max = Integer.MIN_VALUE;

        // cnt 증가 시 까지
        while (cnt < n) {
            // 후보군들 중 가장 작은 것 찾기
            max = Integer.MIN_VALUE;
            int maxRow = 0;

            for (int i = 0; i < n; i++) {
                // 이미 다 사용한 열은 스킵
                if (idxs[i] < 0) continue;
                // 더 작은 경우 이번 시기의 최소값과 그 위치를 저장
                if (max < numbers[idxs[i]][i]) {
                    max = numbers[idxs[i]][i];
                    maxRow = i;
                }
            }
            // 개수 증가, 해당 열의 순서 증가
            cnt++;
            idxs[maxRow]--;
        }
        System.out.println(max);
    }
}
