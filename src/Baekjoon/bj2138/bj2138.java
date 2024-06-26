package Baekjoon.bj2138;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2138
// 2138 전구와 스위치

public class bj2138 {

    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int[] start = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int[] end = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        boolean isDone = true;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (start[i] != end[i]) {
                isDone = false;
                break;
            }
        }

        if (isDone) {
            min = 0;
        }
        else {
            int cnt = 0;

            int[] tmp = Arrays.copyOf(start, n);

            // 첫번째 스위치 누르지 않는 경우
            for (int i = 1; i < n; i++) {
                // 지나간 전구 확인, 이미 확정된 경우라면 스킵
                if (tmp[i - 1] == end[i - 1]) continue;

                // 값이 다른 경우 바꿀 수 있는 마지막 기회기에 버튼 클릭
                cnt++;

                tmp[i - 1] = 1 - tmp[i - 1];
                tmp[i] = 1 - tmp[i];
                if (i < n - 1) tmp[i + 1] = 1 - tmp[i + 1];
            }

            if (tmp[n - 1] == end[n - 1]) min = cnt;

            // 카운터 초기화
            cnt = 1;

            tmp = Arrays.copyOf(start, n);

            // 첫번째 스위치를 누르는 경우
            tmp[0] = 1 - tmp[0];
            tmp[1] = 1 - tmp[1];

            for (int i = 1; i < n; i++) {
                // 지나간 전구 확인, 이미 확정된 경우라면 스킵
                if (tmp[i - 1] == end[i - 1]) continue;

                // 값이 다른 경우 바꿀 수 있는 마지막 기회기에 버튼 클릭
                cnt++;

                tmp[i - 1] = 1 - tmp[i - 1];
                tmp[i] = 1 - tmp[i];
                if (i < n - 1) tmp[i + 1] = 1 - tmp[i + 1];
            }

            if (tmp[n - 1] == end[n - 1]) min = Math.min(min, cnt);
        }

        if (min == Integer.MAX_VALUE) min = -1;

        System.out.println(min);
    }
}
