package Baekjoon.bj11724;

// https://www.acmicpc.net/problem/11724
// 11724 연결 요소의 개수

import java.io.*;
import java.util.*;

public class bj11724 {

    static int n;
    static int m;

    static int[][] connected;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        connected = new int[n][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            connected[u - 1][v - 1] = 1;
            connected[v - 1][u - 1] = 1;
        }

        boolean[] visited = new boolean[n];
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            cnt++;
            Queue<Integer> q = new LinkedList<>();

            q.add(i);
            visited[i] = true;

            while (!q.isEmpty()) {
                int u = q.poll();

                for (int j = 0; j < n; j++) {
                    if(u == j) continue;
                    if (connected[u][j] != 1 || visited[j]) continue;

                    q.add(j);
                    visited[j] = true;
                }
            }

        }

        System.out.println(cnt);
    }
}
