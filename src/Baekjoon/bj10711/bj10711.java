package Baekjoon.bj10711;

// https://www.acmicpc.net/problem/10711
// 10711 모래성

import java.io.*;
import java.util.*;

public class bj10711 {

    static int h, w;
    static String[][] map;

    // 상 우상 우 우하 하 좌하 좌 좌상
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new String[h][w];

        for (int i = 0; i < h; i++) {
            map[i] = br.readLine().split("");
        }

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // 빈 곳이 아니거나 이미 간 곳이라면 스킵
                if (!map[i][j].equals(".")) continue;
                q.add(new int[] {i,j});

            }
        }

        int ret = -1;
        while (!q.isEmpty()) {
            ret++;
            int prevSize = q.size();

            for (int i = 0; i < prevSize; i++) {
                int[] cur = q.poll();

                for (int k = 0; k < 8; k++) {
                    int nr = cur[0] + dr[k];
                    int nc = cur[1] + dc[k];

                    // 벗어난 경우 스킵
                    if (nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
                    // 이미 무너진 곳이라면 스킵
                    if (map[nr][nc].equals(".")) continue;
                    // 모래성인 경우 카운트 감소
                    int wavedNum = Integer.parseInt(map[nr][nc]) - 1;
                    if (wavedNum == 0) {
                        map[nr][nc] = ".";
                        q.add(new int[] {nr,nc});
                    }
                    else map[nr][nc] = String.valueOf(wavedNum);
                }
            }
        }

        System.out.println(ret);
    }
}
