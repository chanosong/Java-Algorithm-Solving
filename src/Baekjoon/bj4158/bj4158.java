package Baekjoon.bj4158;

// https://www.acmicpc.net/problem/4158
// CD

import java.io.*;
import java.util.*;

public class bj4158 {
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (true) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            Set<Integer> set = new HashSet<>();

            for (int i = 0; i < n; i++) {
                set.add(Integer.parseInt(br.readLine()));
            }

            int answer = 0;

            for (int i = 0; i < m; i++) {
                if (set.contains(Integer.parseInt(br.readLine()))) answer++;
            }

            System.out.println(answer);
        }

    }
}
