package Baekjoon.bj1926;

// 1926 그림
// https://www.acmicpc.net/problem/1926


import java.io.*;
import java.util.*;

public class bj1926 {

    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static int paintNum = 0;
    static int maxSize = 0;

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0, -1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        n = input[0];
        m = input[1];

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // BFS 시작
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 이미 방문했거나, 0인 경우에는 스킵
                if (visited[i][j] || map[i][j] == 0) continue;

                // BFS 시작
                Queue<Point> q = new LinkedList<>();
                q.add(new Point(i,j));
                visited[i][j] = true;

                int nowSize = 0;
                paintNum++;

                while (!q.isEmpty()) {
                    Point now = q.poll();
                    // 그림 사이즈 증가
                    nowSize++;

                    for (int k = 0; k < 4; k++) {
                        int nr = now.r + dr[k];
                        int nc = now.c + dc[k];

                        // 범위를 벗어난 경우 스킵
                        if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                        // 이미 방문했거나 그림 영역이 아닌 경우도 스킵
                        if (visited[nr][nc] || map[nr][nc] == 0) continue;

                        // 새롭게 가는 영역이라면 진행
                        q.add(new Point(nr,nc));
                        visited[nr][nc] = true;
                    }
                }

                // 최대 크기 비교
                maxSize = Math.max(maxSize, nowSize);
            }
        }

        System.out.println(paintNum);
        System.out.println(maxSize);
    }

    static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
