package Baekjoon.bj17142;

import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/17142
// 연구소 3


public class bj17142 {

    static int n;
    static int m;
    static int[][] map;
    static List<Point> points = new ArrayList<>();
    static boolean[] picked;
    static int wall = 0;
    static int answer = Integer.MAX_VALUE;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // Point 위치 저장
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 2) {
                    points.add(new Point(i, j,0));
                }
                else if (map[i][j] == 1) {
                    wall++;
                }
            }
        }

        picked = new boolean[points.size()];

        if (points.size() + wall == n*n) answer = 0;
        else recursive(0,0, picked);

        if (answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }

    // 전체 바이러스 중 m개 선택
    static void recursive(int start, int pickCnt, boolean[] picked) {
        // m개를 모두 고른 경우
        if (pickCnt == m) {
            virus(picked);
            return;
        }

        // 넘어간 경우 종료
        if (start >= points.size()) return;

        // 더 선택하는 경우
        for (int i = start; i < points.size(); i++) {
            picked[i] = true;
            recursive(i + 1, pickCnt + 1, picked);
            picked[i] = false;
        }
    }

    // 바이러스 확산 시작
    static void virus(boolean[] picked) {

        // 바이러스 활성화 구역 카운터
        int cnt = 0;
        int time = 0;
        boolean[][] visited = new boolean[n][n];
        int[][] timemap = new int[n][n];

        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < points.size(); i++) {
            if (picked[i]) {
                Point nv = points.get(i);
                q.add(nv);

                // 활성화 처리
                cnt++;
                visited[nv.r][nv.c] = true;
                timemap[nv.r][nv.c] = nv.t;
            }
        }
        
        // BFS 시작
        while (!q.isEmpty()) {
            Point now = q.poll();

            // 활성화 처리
            time = now.t;

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                int nt = now.t + 1;

                // 벗어난 경우 스킵
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

                // 이미 방문한 경우, 벽인 경우, 스킵
                if (visited[nr][nc] || map[nr][nc] == 1) continue;

                q.add(new Point(nr, nc, nt));

                // 활성화 처리
                // 기존 빈 공간이었던 경우만 카운트 증가
                if (map[nr][nc] == 0) cnt++;

                visited[nr][nc] = true;
                timemap[nr][nc] = nt;

                // 모든 구역을 바이러스 활성화 시킨 경우
                if (n * n - wall - (points.size() - m) <= cnt) {
                    answer = Math.min(answer, nt);
                    break;
                }
            }
        }

//        printMap(timemap);
    }

    static void printMap(int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Point {
    int r;
    int c;
    int t;

    public Point(int r, int c, int time) {
        this.r = r;
        this.c = c;
        t = time;
    }
}
