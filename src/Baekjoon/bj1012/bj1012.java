package Baekjoon.bj1012;

// https://www.acmicpc.net/problem/1012
// 유기농 배추

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj1012 {

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int _t = 0; _t < t; _t++) {
            // 가로 세로 배추 개수
            int m = sc.nextInt();
            int n = sc.nextInt();
            int k = sc.nextInt();

            int amount = 0;

            int[][] map = new int[n][m];
            boolean[][] visited = new boolean[n][m];

            Queue<Point> points = new LinkedList<>();

            // 버퍼 초기화
            sc.nextLine();

            for (int _k = 0; _k < k; _k++) {
                int[] point = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                map[point[1]][point[0]] = 1;
                points.add(new Point(point[1], point[0]));
            }

            while (!points.isEmpty()) {
                Point now = points.poll();

                // 이미 체크를 했다면 스킵
                if (visited[now.r][now.c]) continue;

                // 아닌 경우 해당 카운트 증가 후 BFS 시작
                amount++;

                Queue<Point> togo = new LinkedList<>();
                visited[now.r][now.c] = true;
                togo.add(now);

                while (!togo.isEmpty()) {
                    Point _now = togo.poll();

                    for (int i = 0; i < 4; i++) {
                        int nr = _now.r + dr[i];
                        int nc = _now.c + dc[i];

                        // 범위를 벗어난 경우 스킵
                        if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                        // 이미 이동한 곳이거나 배추가 아닌 경우도 스킵
                        if (map[nr][nc] != 1 || visited[nr][nc]) continue;

                        togo.add(new Point(nr,nc));
                        visited[nr][nc] = true;
                    }
                }
            }

            System.out.println(amount);
        }
    }
}

class Point {
    int r;
    int c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}