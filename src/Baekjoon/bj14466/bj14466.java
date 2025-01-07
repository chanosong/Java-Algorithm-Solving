package Baekjoon.bj14466;

// https://www.acmicpc.net/problem/14466
// 14466 소가 길을 건너간 이유 6

import java.io.*;
import java.util.*;

public class bj14466 {

    static int[][] map;
    static boolean[][] canMeet;
    static boolean[][][] cantMove;

    // 상 하 좌 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];
        cantMove = new boolean[n + 1][n + 1][4];
        canMeet = new boolean[k + 1][k + 1];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int d1 = 0;

            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int d2 = 0;

            int dr1 = r1 - r2;
            int dc1 = c1 - c2;

            switch (dr1) {
                case 0:
                    if (dc1 == -1) d1 = 3;
                    else d1 = 2;
                    break;
                case 1:
                    d1 = 0;
                    break;
                case -1:
                    d1 = 1;
            }

            switch (d1) {
                case 0:
                    d2 = 1;
                    break;
                case 1:
                    d2 = 0;
                    break;
                case 2:
                    d2 = 3;
                    break;
                case 3:
                    d2 = 2;
            }

            cantMove[r1][c1][d1] = true;
            cantMove[r2][c2][d2] = true;
        }

        int cnt = 1;
        List<Point> list = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int tr = Integer.parseInt(st.nextToken());
            int tc = Integer.parseInt(st.nextToken());

            map[tr][tc] = cnt++;
            list.add(new Point(tr, tc));
        }

        for (int idx = 0; idx < k; idx++) {
            Point start = list.get(idx);

            Queue<Point> q = new LinkedList<>();
            q.add(start);

            boolean[][] visited = new boolean[n + 1][n + 1];

            while (!q.isEmpty()) {
                Point cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    if (cantMove[cur.r][cur.c][i]) continue;

                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];

                    if (nr < 1 || nc < 1 || nr >= n + 1 || nc >= n + 1) continue;
                    if (visited[nr][nc]) continue;

                    // 이어지는 경우 체킹
                    if (map[nr][nc] > 0) {
                        canMeet[idx + 1][map[nr][nc]] = true;
                        canMeet[map[nr][nc]][idx + 1] = true;
                    }

                    q.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= k; i++) {
            for (int j = i + 1; j <= k; j++) {
                if (!canMeet[i][j]) answer++;
            }
        }

        System.out.println(answer);
    }
}

class Point {
    int r;
    int c;

    Point (int r, int c) {
        this.r = r;
        this.c = c;
    }
}