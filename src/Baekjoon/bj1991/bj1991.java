package Baekjoon.bj1991;

// https://www.acmicpc.net/problem/1991
// 1991 트리 순회

import java.io.*;
import java.util.*;

public class bj1991 {

    static int n;
    static int[][] child = new int[27][2];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char s1 = st.nextToken().charAt(0);
            char s2 = st.nextToken().charAt(0);
            char s3 = st.nextToken().charAt(0);

            if (s2 != '.') child[s1 - 'A'][0] = s2 - 'A';
            else child[s1 - 'A'][0] = -1;

            if (s3 != '.') child[s1 - 'A'][1] = s3 - 'A';
            else child[s1 - 'A'][1] = -1;
        }

        StringBuffer sb1 = new StringBuffer();

        recursive1(0, sb1);

        StringBuffer sb2 = new StringBuffer();

        recursive2(0, sb2);

        StringBuffer sb3 = new StringBuffer();

        recursive3(0, sb3);

        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
        System.out.println(sb3.toString());
    }

    static void recursive1(int idx, StringBuffer sb) {
        sb.append((char) (idx + 'A'));

        for (int i = 0; i < 2; i++) {
            if (child[idx][i] == -1) continue;
            recursive1(child[idx][i], sb);
        }
    }

    static void recursive2(int idx, StringBuffer sb) {
        for (int i = 0; i < 2; i++) {
            if (i == 1) sb.append((char) (idx + 'A'));
            if (child[idx][i] == -1) continue;
            recursive2(child[idx][i], sb);
        }
    }

    static void recursive3(int idx, StringBuffer sb) {
        for (int i = 0; i < 2; i++) {
            if (child[idx][i] == -1) continue;
            recursive3(child[idx][i], sb);
        }
        sb.append((char) (idx + 'A'));
    }
}
