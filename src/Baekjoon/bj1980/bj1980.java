package Baekjoon.bj1980;

// https://www.acmicpc.net/problem/1980
// 1980 햄버거 사랑

import java.io.*;
import java.util.*;

public class bj1980 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int small = Math.min(n, m);
        int big = Math.max(n, m);

        int ans1 = 0;
        int ans2 = 0;
        int remain = t;

        boolean isDone = false;

        for (int r = 0; r < t; r++) {
            int tmp1 = (t - r) / small;
            int tmp2 = ((t - r) % small) / big;
            int tmp3 = ((t - r) % small) % big;

            if (r == 0 || tmp3 == 0) {
                ans1 = tmp1;
                ans2 = tmp2;
                if (ans1 + ans2 != 0) remain = r;

                if (tmp3 == 0) break;
            }

            while (tmp1 > 0) {
                tmp1--;
                tmp2 = ((t - r) - small * tmp1) / big;
                tmp3 = ((t - r) - small * tmp1) % big;

                if (tmp3 == 0) {
                    ans1 = tmp1;
                    ans2 = tmp2;
                    remain = r;
                    isDone = true;
                    break;
                }
            }

            if (isDone) break;
        }

        System.out.println((ans1 + ans2) + " " + remain);
    }
}
