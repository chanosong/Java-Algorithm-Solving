package Baekjoon.bj2206;

// 2206 벽 부수고 이동하기
// https://www.acmicpc.net/problem/2206

import java.util.*;
import java.io.*;

public class bj2206 {
    static int n;
    static int m;

    static int answer = -1;

    static int[][] map;
    static boolean[][][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        Queue<Status> q = new LinkedList<>();

        q.add(new Status(0,0,1, true));
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while (!q.isEmpty()) {
            Status s = q.poll();

            // 도착한 경우
            if (s.r == n - 1 && s.c == m - 1) {
                answer = s.cnt;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = s.r + dr[i];
                int nc = s.c + dc[i];
                int ncnt = s.cnt + 1;
                boolean nCanBreak = s.canBreak;

                // 범위를 벗어난 경우
                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                // 이미 간 곳인 경우 - 부수기 가능
                if (s.canBreak && visited[nr][nc][0]) continue;
                // 이미 간 곳인 경우 - 부수기 불가
                if (!s.canBreak && visited[nr][nc][1]) continue;
                // 더 이상 못 뚫는 경우
                if (map[nr][nc] == 1) {
                    if (!s.canBreak) continue;

                    nCanBreak = false;
                }

                q.add(new Status(nr, nc, ncnt, nCanBreak));
                if (nCanBreak) visited[nr][nc][0] = true;
                else visited[nr][nc][1] = true;
            }
        }

        System.out.println(answer);
    }
}

class Status {
    int r;
    int c;
    int cnt;
    boolean canBreak;

    public Status(int r, int c, int cnt, boolean canBreak) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
        this.canBreak = canBreak;
    }
}