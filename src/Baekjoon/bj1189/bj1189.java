package Baekjoon.bj1189;

// https://www.acmicpc.net/problem/1189
// 1189 컴백홈

import java.io.*;
import java.util.*;

public class bj1189 {
    
    // 상하좌우
    static int[] dr = {-1 ,1 ,0, 0};
    static int[] dc = {0, 0, -1, 1};

    static String[][] map;
    static boolean[][] visited;

    static int r, c, k;
    static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new String[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().split("");
        }

        dfs(r - 1, 0, 1);

        System.out.println(answer);
    }

    static void dfs (int rr, int cc, int cnt) {
        // 방문 처리
        visited[rr][cc] = true;

        // 종점인 경우 길이 계산 후 처리
        if (rr == 0 && cc == c - 1) {
            if (cnt == k) answer++;
        }
        // 종점이 아닌 경우 계속 진행
        else {
            for (int i = 0; i < 4; i++) {
                int nr = rr + dr[i];
                int nc = cc + dc[i];

                // 벗어난 경우 스킵
                if (nr < 0 || nc < 0 || nr >= r || nc >= c) continue;
                // 이미 방문한 경우, T 인경우 스킵
                if (visited[nr][nc] || map[nr][nc].equals("T")) continue;

                visited[nr][nc] = true;
                dfs(nr, nc, cnt + 1);
                visited[nr][nc] = false;
            }
        }
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