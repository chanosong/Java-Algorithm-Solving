package Baekjoon.bj7576;

// https://www.acmicpc.net/problem/7576
// 토마토

import java.io.*;
import java.util.*;

public class bj7576 {

    static int m, n;
    static int[][] map;
    static int[][] time;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int cnt = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        time = new int[n][m];

        Queue<Procedure> q = new LinkedList<>();


        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) q.add(new Procedure(i, j, 0));
                else time[i][j] = -1;
            }
        }

        while (!q.isEmpty()) {
            Procedure p = q.poll();

            cnt = Math.max(cnt, p.t);

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                int nt = p.t + 1;

                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                if (map[nr][nc] == -1 || map[nr][nc] == 1 && time[nr][nc] <= nt) continue;

                map[nr][nc] = 1;
                time[nr][nc] = nt;
                q.add(new Procedure(nr, nc, nt));
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(cnt);
    }
}

class Procedure {
    int r;
    int c;
    int t;

    Procedure (int r, int c, int t) {
        this.r = r;
        this.c = c;
        this.t = t;
    }
}