package Baekjoon.bj1863;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1863
// 1863 스카이라인 쉬운거

public class bj1863 {

    static int n;
    static int[] x;
    static int[] y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        x = new int[n + 1];
        y = new int[n + 1];

        Stack<Integer> st = new Stack<>();

        int answer = 0;

        for (int i = 0; i <= n; i++) {
            if (i < n) {
                String[] s = br.readLine().split(" ");

                x[i] = Integer.parseInt(s[0]);
                y[i] = Integer.parseInt(s[1]);
            }

            // 더 큰 건물이 들어온 경우 이전 건물 정의 종료
            while (!st.isEmpty() && st.peek() > y[i]) {
                st.pop();
                answer++;
            }

            // 같은 높이인 경우 같은 건물 진행
            if (!st.isEmpty() && st.peek() == y[i]) continue;

            st.push(y[i]);
        }

        while(!st.isEmpty()) {
            if (st.peek() > 0) {
                answer++;
            }
            st.pop();
        }

        System.out.println(answer);
    }
}
