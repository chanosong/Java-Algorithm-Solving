package Baekjoon.bj5427;

// https://www.acmicpc.net/problem/5427
// 5427 불

import java.io.*;
import java.util.*;

public class bj5427_2 {

    static int t,w,h;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            String[][] map = new String[h][w];

            for (int j = 0; j < h; j++) {
                map[j] = br.readLine().split("");
            }

            Queue<int[]> fireList = new LinkedList<>();
            int sr = - 1;
            int sc = - 1;

            for (int j = 0; j < h; j++) {
                for (int k = 0; k < w; k++) {
                    // 불인 곳 체크
                    if (map[j][k].equals("*")) {
                        fireList.add(new int[]{j,k});
                    }
                    else if (map[j][k].equals("@")) {
                        sr = j;
                        sc = k;
                    }
                }
            }

            Queue<int[]> q = new LinkedList<>();

            boolean[][] visited = new boolean[h][w];

            String answer = "IMPOSSIBLE";

            q.add(new int[]{sr,sc,0});
            visited[sr][sc] = true;

            while (!q.isEmpty()) {
                Queue<int[]> fq = new LinkedList<>();

                // 선불
                while (!fireList.isEmpty()) {
                    int[] cur = fireList.poll();

                    for (int j = 0; j < 4; j++) {
                        int nr = cur[0] + dr[j];
                        int nc = cur[1] + dc[j];

                        if (nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
                        if (map[nr][nc].equals("#") || map[nr][nc].equals("*")) continue;

                        map[nr][nc] = "*";
                        fq.add(new int[]{nr,nc});
                    }
                }

                // 교체
                fireList = fq;

                Queue<int[]> q2 = new LinkedList<>();
                // 후성근
                while (!q.isEmpty()) {
                    int[] cur = q.poll();

                    if (cur[0] == 0 || cur[1] == 0 || cur[0] >= h - 1|| cur[1] >= w - 1) {
                        answer = String.valueOf(cur[2] + 1);
                        break;
                    }

                    for (int j = 0; j < 4; j++) {
                        int nr = cur[0] + dr[j];
                        int nc = cur[1] + dc[j];
                        int nt = cur[2] + 1;

                        if (nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
                        if (map[nr][nc].equals("#") || map[nr][nc].equals("*") || visited[nr][nc]) continue;

                        visited[nr][nc] = true;
                        q2.add(new int[]{nr,nc,nt});
                    }
                }

                // 교체
                q = q2;
                if (!answer.equals("IMPOSSIBLE")) break;
            }

            System.out.println(answer);
        }
    }
}
