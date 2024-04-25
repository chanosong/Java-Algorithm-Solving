package Baekjoon.bj1027;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1027
// 1027 고층 건물

public class bj1027 {

    static int n;
    static int[] buildings;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        buildings = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < n; i++) {
            double ld = Double.MIN_VALUE;
            double rd = Double.MIN_VALUE;
            int leftCnt = 0;
            int rightCnt = 0;

            // 자신 기준 왼쪽 확인
            if (i > 0) {
                ld = (buildings[i - 1] - buildings[i]);
                leftCnt++;
            }

            for (int j = i - 2; j >= 0; j--) {
                if (ld < (double) (buildings[j] - buildings[i]) / (i - j)) {
                    ld = (double) (buildings[j] - buildings[i]) / (i - j);
                    leftCnt++;
                }
            }

            // 자신 기준 오른쪽 확인
            if (i < n - 1) {
                rd = (buildings[i + 1] - buildings[i]);
                rightCnt++;
            }

            for (int j = i + 2; j < n; j++) {
                if (rd < (double) (buildings[j] - buildings[i]) / (j - i)) {
                    rd = (double) (buildings[j] - buildings[i]) / (j - i);
                    rightCnt++;
                }
            }

            answer = Math.max(answer, leftCnt + rightCnt);
        }

        System.out.println(answer);
    }
}
