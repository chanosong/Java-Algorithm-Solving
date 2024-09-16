package Baekjoon.bj24542;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/24542
// 24542 튜터-튜티 관계의 수

public class bj24542 {

    static List<List<Integer>> map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }

        visited = new boolean[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map.get(u-1).add(v - 1);
            map.get(v-1).add(u - 1);
        }

        long answer = 1;

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            Queue<Integer> queue = new LinkedList<>();

            queue.add(i);
            visited[i] = true;

            int size = 1;

            while (!queue.isEmpty()) {

                int idx = queue.poll();

                for (int j : map.get(idx)) {
                    if (visited[j]) continue;

                    queue.add(j);
                    visited[j] = true;
                    size++;
                }
            }

            answer *= size;
            answer %= 1000000007;
        }

        System.out.println(answer);
    }
}
