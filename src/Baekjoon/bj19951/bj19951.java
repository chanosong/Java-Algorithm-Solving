package Baekjoon.bj19951;

// https://www.acmicpc.net/problem/19951
// 19951 태상이의 훈련소 생활

import java.io.*;
import java.util.*;

public class bj19951 {

    static int n;
    static int m;
    static int[] h;

    static int[] d;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        h = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        d = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int dd = Integer.parseInt(st.nextToken());

            d[s - 1] += dd;
            d[e] += dd * (-1);
        }

        int diff = 0;

        for (int i = 0; i < n; i++) {
            diff += d[i];
            h[i] += diff;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(h[i] + " ");
        }
    }
}
