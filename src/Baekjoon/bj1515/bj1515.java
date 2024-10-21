package Baekjoon.bj1515;

import java.io.*;
import java.util.*;

// 1515 수 이어 쓰기
// https://www.acmicpc.net/problem/1515

public class bj1515 {

    static String[] n;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = br.readLine().split("");

        int cnt = 1;
        int idx = 0;

        while (idx < n.length) {
            boolean isDone = false;
            String[] s = String.valueOf(cnt).split("");

            for (String ss : s) {
                if (idx == n.length) {
                    isDone = true;
                    break;
                }
                if (ss.equals(n[idx])) {
                    idx++;
                }
            }
            if (isDone || idx == n.length) break;

            cnt++;
        }

        System.out.println(cnt);
    }
}
