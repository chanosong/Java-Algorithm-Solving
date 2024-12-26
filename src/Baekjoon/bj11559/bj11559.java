package Baekjoon.bj11559;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11559
// 11559 Puyo Puyo

public class bj11559 {

    static int r = 12;
    static int c = 6;

    static String[][] map = new String[r][c];

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().split("");
        }

        while (explode()) {
            gravity();
        }

        System.out.println(answer);
    }

    static void gravity() {
        for (int j = 0; j < c; j++) {
            int bottom = -1;
            for (int i = r - 1; i >= 0; i--) {
                // bottom이 나오지 않은 경우
                if (bottom == - 1) {
                    if (map[i][j].equals(".")) bottom = i;
                }
                // bottom이 나온 경우
                else {
                    if (!map[i][j].equals(".")) {
                        map[bottom][j] = map[i][j];
                        map[i][j] = ".";

                        while (bottom >= 0) {
                            if (!map[bottom][j].equals(".")) bottom--;
                            else break;
                        }
                    }
                }
            }
        }
    }

    static boolean explode() {

        boolean isExploded = false;

        for (int i = r - 1; i >= 0; i--) {
            for (int j = 0; j < c; j++) {
                if (map[i][j].equals(".")) continue;

                String color = map[i][j];

                Queue<int[]> q = new LinkedList<>();
                List<int[]> list = new ArrayList<>();

                q.add(new int[] {i, j});
                map[i][j] = ".";
                list.add(new int[] {i, j});

                while (!q.isEmpty()) {
                    int[] cur = q.poll();

                    for (int k = 0; k < 4; k++) {
                        int nr = cur[0] + dr[k];
                        int nc = cur[1] + dc[k];

                        if (nr < 0 || nc < 0 || nr >= r || nc >= c) continue;
                        if (!map[nr][nc].equals(color)) continue;

                        q.add(new int[] {nr, nc});
                        map[nr][nc] = ".";
                        list.add(new int[] {nr, nc});
                    }
                }

                if (list.size() >= 4) isExploded = true;
                else {
                    for (int[] cur : list) {
                        map[cur[0]][cur[1]] = color;
                    }
                }
            }
        }

        if (isExploded) answer++;

        return isExploded;
    }
}
