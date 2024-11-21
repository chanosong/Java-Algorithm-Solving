package Baekjoon.bj16987;

// https://www.acmicpc.net/problem/16987
// 계란으로 계란치기

import java.io.*;
import java.util.*;

public class bj16987 {

    static int n;

    static int[] durability;
    static int[] weight;

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        durability = new int[n];
        weight = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            durability[i] = d;
            weight[i] = w;
        }

        int handheldIdx = 0;

        recursive(handheldIdx, 0);

        System.out.println(answer);
    }

    static void recursive(int handIdx, int crashCnt) {
        // 들 계란이 없는 경우
        if (handIdx == n) {
            answer = Math.max(answer, crashCnt);
            return;
        }

        // 손에 들고있는 계란이 깨진 경우
        if (durability[handIdx] <= 0) {
            recursive(handIdx + 1, crashCnt);
            return;
        }

        // 손에 들고있는 계란이 살아있는 경우
        for (int i = 0; i < n; i++) {
            // 이미 손에 들고있는 경우
            if (handIdx == i) continue;
            // 이미 깨진 경우
            else if (durability[i] <= 0) continue;

            durability[i] -= weight[handIdx];
            durability[handIdx] -= weight[i];

            if (durability[i] <= 0) crashCnt++;
            if (durability[handIdx] <= 0) crashCnt++;

            recursive(handIdx + 1, crashCnt);

            // 수복
            if (durability[i] <= 0) crashCnt--;
            if (durability[handIdx] <= 0) crashCnt--;

            durability[handIdx] += weight[i];
            durability[i] += weight[handIdx];
        }

        answer = Math.max(answer, crashCnt);
    }
}
