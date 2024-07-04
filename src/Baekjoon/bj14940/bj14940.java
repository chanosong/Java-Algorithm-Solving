package Baekjoon.bj14940;

import java.io.*;
import java.util.*;

// 14940 쉬운 최단거리
// https://www.acmicpc.net/problem/14940

public class bj14940 {

    static int n;
    static int m;

    static int[][] map;
    static int[][] answer;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int sr;
    static int sc;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        answer = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    sr = i;
                    sc = j;
                }
            }

            Arrays.fill(answer[i], -2);
        }

        Queue<Point> q = new LinkedList<>();

        q.add(new Point(sr,sc,0));
        answer[sr][sc] = 0;

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                int nd = now.d + 1;

                if (nr < 0 || nc < 0 || nd < 0 || nr >= n || nc >= m) continue;
                if (map[nr][nc] == 0 || answer[nr][nc] != -2) continue;

                q.add(new Point(nr,nc,nd));
                answer[nr][nc] = nd;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (answer[i][j] == -2) {
                    if (map[i][j] == 1) {
                        answer[i][j] = -1;
                    }
                    else if (map[i][j] == 0) {
                        answer[i][j] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class Point {
    int r;
    int c;
    int d;

    Point(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}
