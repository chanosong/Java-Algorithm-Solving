package Baekjoon.bj1260;

import java.util.*;

// https://www.acmicpc.net/problem/1260
public class bj1260 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int v = sc.nextInt();

        boolean[][] vertex = new boolean[n][n];
        boolean[][] copyVertex = new boolean[n][n];

        for (int i = 0; i < m; i++) {
            int tx = sc.nextInt();
            int ty = sc.nextInt();

            vertex[tx - 1][ty - 1] = true;
            vertex[ty - 1][tx - 1] = true;
        }

        boolean[] visited1 = new boolean[n];
        boolean[] visited2 = new boolean[n];

        for (int i = 0; i < n; i++) {
            copyVertex[i] = Arrays.copyOf(vertex[i], vertex[i].length);
        }

        // DFS
        visited1[v - 1] = true;
        dfs(copyVertex, visited1, v - 1);
        System.out.println();

        // BFS
        visited2[v - 1] = true;
        bfs(vertex, visited2, v - 1);
    }

    static void dfs(boolean[][] vertex, boolean[] visited, int start) {
        System.out.print((start + 1) + " ");

        int next = -1;

        // 가장 작은 순으로 연결된 것 확인
        for (int i = 0; i < vertex[start].length; i++) {
            // 연결된 곳이 있다면 다음 출발지 세팅
            if (vertex[start][i] && !visited[i]) {
                next = i;
                visited[i] = true;
                vertex[start][i] = false;
                vertex[i][start] = false;
                dfs(vertex, visited, next);
            }
        }
    }

    static void bfs(boolean[][] vertex, boolean[] visited, int start) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            System.out.print((now + 1) + " ");

            for (int i = 0; i < vertex[now].length; i++) {
                // 연결된 곳이 있다면 다음 출발지 세팅
                if (vertex[now][i] && !visited[i]) {
                    visited[i] = true;
                    vertex[now][i] = false;
                    vertex[i][now] = false;

                    queue.add(i);
                }
            }
        }
    }
}
