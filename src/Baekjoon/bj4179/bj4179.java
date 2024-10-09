package Baekjoon.bj4179;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/4179
// 4179 불!

public class bj4179 {

    static int r, c;
    static String[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new String[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().split("");
        }

        // 타임 좌표
        int[][] timeMap = new int[r][c];

        // 지훈 좌표
        int jr = 0;
        int jc = 0;
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j].equals("#")) {timeMap[i][j] = -2;}
                else if (map[i][j].equals("J")) {
                    jr = i;
                    jc = j;
                }

                if (map[i][j].equals("J") || map[i][j].equals(".")) { timeMap[i][j] = -1;}
            }
        }

        int time = 0;

        // 불 번지기 시작
        while (true) {
            boolean isBurn = false;

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    // 현재 시각에 해당하는 곳이 아닌 경우
                   if (timeMap[i][j] != time) continue;

                   // 번질 곳인 경우
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        // 벗어난 경우 스킵
                        if (nr < 0 || nc < 0 || nr >= r || nc >= c) continue;

                        // 이미 불이 번진 경우나 벽인 경우 스킵 (지훈이 있던 장소는 번질 수 있음)
                        if (timeMap[nr][nc] == -2 || (timeMap[nr][nc] >= 0 && timeMap[nr][nc] < time)) continue;

                        if (timeMap[nr][nc] >= 0) timeMap[nr][nc] = Math.min(timeMap[nr][nc] ,time + 1);
                        else timeMap[nr][nc] = time + 1;

                        isBurn = true;
                    }
                }
            }
            
            // 시간 증가
            time++;

            // 새롭게 번진 곳이 없는 경우 종료
            if (!isBurn) break;
        }

        // 옮기기
        Queue<Status> q = new LinkedList<>();

        q.add(new Status(jr,jc,0));

        boolean[][] visited = new boolean[r][c];

        visited[jr][jc] = true;

        while (!q.isEmpty()) {
            Status s = q.poll();

            // 만약 벽에 붙어있는 경우 탈출 성공
            if (s.r == 0 || s.r == r - 1 || s.c == 0 || s.c == c - 1) {
                System.out.println(s.time + 1);
                return;
            }

            for (int k = 0; k < 4; k++) {
                int nr = s.r + dr[k];
                int nc = s.c + dc[k];
                int nt = s.time + 1;

                // 벗어난 경우 스킵
                if (nr < 0 || nc < 0 || nr >= r || nc >= c) continue;

                // 이미 간 경우 스킵
                if (visited[nr][nc]) continue;

                // 벽이거나 이미 불인 경우 스킵
                if (timeMap[nr][nc] == -2 || (timeMap[nr][nc] >= 0 && timeMap[nr][nc] <= nt)) continue;

                q.add(new Status(nr,nc,nt));
                visited[nr][nc] = true;
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}

class Status {
    int r;
    int c;
    int time;

    Status(int r, int c, int time) {
        this.r = r;
        this.c = c;
        this.time = time;
    }
}
