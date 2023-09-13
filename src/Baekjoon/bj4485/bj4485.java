package Baekjoon.bj4485;
// 4485 녹색 옷 입은 애가 젤다지?
// https://www.acmicpc.net/problem/4485

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj4485 {
    static int n;
    static int[][] map;
    static int[][] score;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseCnt = 1;

        while (true) {
            n = Integer.parseInt(br.readLine());

            if (n == 0) break;

            map = new int[n][n];

            //  map 설정
            for (int i = 0; i < n; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            score = new int[n][n];

            for (int i = 0; i < n; i++) {
                Arrays.fill(score[i], Integer.MAX_VALUE);
            }

            score[0][0] = map[0][0];

            PriorityQueue<Point> pq = new PriorityQueue<>();
            pq.add(new Point(0,0, score[0][0]));

            while (!pq.isEmpty()) {
                Point now = pq.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];

                    // 범위를 벗어난 경우
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                    int newScore = now.rupee + map[nr][nc];

                    // 이득이 아닌 경우
                    if (score[nr][nc] <= newScore) continue;

                    // 더 낮은 값인 경우
                    pq.add(new Point(nr,nc, newScore));
                    score[nr][nc] = newScore;
                }
            }

            System.out.println("Problem " + caseCnt++ + ": " + score[n-1][n-1]);

            /*
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(score[i][j] + " ");
                }
                System.out.println();
            }

             */
        }
    }
}

class Point implements Comparable<Point>{
    int r;
    int c;
    int rupee;

    Point(int r, int c, int rupee) {
        this.r = r;
        this.c = c;
        this.rupee = rupee;
    }

    @Override
    public int compareTo(Point p) {
        return this.rupee - p.rupee;
    }
}
