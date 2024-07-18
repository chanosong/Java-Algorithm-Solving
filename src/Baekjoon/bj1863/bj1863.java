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

        x = new int[n];
        y = new int[n];

        Stack<Integer> st = new Stack<>();

        int answer = 0;

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");

            x[i] = Integer.parseInt(s[0]);
            y[i] = Integer.parseInt(s[1]);

            if (st.isEmpty() || st.peek() <= y[i]) {
                st.push(y[i]);
            }
            else {
                st.pop();
                answer++;
            }
        }

        while(!st.isEmpty()) {
            st.pop();
            answer++;
        }

        System.out.println(answer);
    }
}
