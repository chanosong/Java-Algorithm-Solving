package Baekjoon.bj13702;

// 13702 이상한 술집
// https://www.acmicpc.net/problem/13702

import java.io.*;
import java.util.*;

public class bj13702 {

    static int n;
    static int k;

    static long[] arr;
    static long[] cnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        arr = new long[n];
        cnt = new long[n];

        long sum = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());

            sum += arr[i];
        }

        long max = sum / k;

        while (true) {

            if (max == 0) break;

            for (int i = 0; i < n; i++) {
                cnt[i] = arr[i] / max;
            }

            long[] tmp = new long[n];

            for (int i = 0; i < n; i++) {
                // 시작부터 안되는 경우 스킵
                if (cnt[i] == 0) continue;
                
                tmp[i] = arr[i] / (cnt[i] + 1);
            }

            int nowCnt = 0;
            int minIdx = 0;
            long tmpMax = 0;

            for (int i = 0; i < n; i++) {

                if (cnt[i] == 0) continue;

                nowCnt += cnt[i];

                if (tmpMax < tmp[i]) {
                    tmpMax = tmp[i];
                    minIdx = i;
                }
            }

            if (k == nowCnt) break;

            max = tmpMax;
            cnt[minIdx]++;
        }

        System.out.println(max);
    }
}
