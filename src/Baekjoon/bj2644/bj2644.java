package Baekjoon.bj2644;
// https://www.acmicpc.net/problem/2644

import java.util.Scanner;

public class bj2644 {
    static int answer = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target1 = sc.nextInt();
        int target2 = sc.nextInt();
        int m = sc.nextInt();

        boolean[][] vertex = new boolean[n][n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            vertex[x - 1][y - 1] = true;
            vertex[y - 1][x - 1] = true;
        }

        // 시작지점 체크
        visited[target1 - 1] = true;
        dfs(target1 - 1, target2 - 1, 0, vertex, visited);

        System.out.println(answer);
    }

    static void dfs(int start, int dest, int depth, boolean[][] vertex, boolean[] visited) {
        // 도달한 경우
        if (start == dest) {
            answer = depth;
            return;
        }

        for (int i = 0; i < vertex[start].length; i++) {
            // 연결되지 않은 경우나 이미 방문한 경우 스킵
            if (vertex[start][i] == false || visited[i]) continue;

            // 갈 수 있는 경우 진입
            visited[i] = true;
            dfs(i, dest, depth + 1, vertex, visited);
            visited[i] = false;
        }
    }
}
