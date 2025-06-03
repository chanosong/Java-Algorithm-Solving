package Baekjoon.bj22233;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/22233
// 가희와 키워드

public class bj22233 {

    static int n, m;
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            set.add(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            String[] str = br.readLine().split(",");

            for (String s : str) {
                set.remove(s);
            }

            System.out.println(set.size());
        }
    }
}
