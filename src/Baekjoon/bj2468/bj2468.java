package Baekjoon.bj2468;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2468
// 안전 영역
public class bj2468 {

    static int n;
    static int[][] map;

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};

    static int min;
    static int max;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 안전 영역 체킹
        int answer = Integer.MIN_VALUE;

        n = sc.nextInt();

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        map = new int[n][n];

        // 버퍼초기화
        sc.nextLine();

        // 입력
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 최소, 최대 높이 체킹
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        // 모든 빌딩이 같다면
        if (min == max) {
            answer = 1;
        }
        else {
            // 최소 ~ 최대 침수 시 안전 영역 체킹
            for (int i = min; i < max; i++) {
                answer = Math.max(answer, getArea(i));
            }
        }

        System.out.println(answer);
    }

    static int getArea(int depth) {
        int cnt = 0;

        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 이미 침수되었거나 방문한 곳이라면 스킵
                if (map[i][j] <= depth || visited[i][j]) continue;
                // 새로운 안전 영역 카운팅
                cnt++;
                // visited 처리
                visited[i][j] = true;
                Queue<Point> queue = new LinkedList<>();
                queue.add(new Point(i,j));

                while(!queue.isEmpty()) {
                    Point now = queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int nr = now.r + dr[k];
                        int nc = now.c + dc[k];

                        // 지도를 벗어나거나 이미 방문했거나 이미 침수된 곳이라면 스킵
                        if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc] || map[nr][nc] <= depth) continue;

                        // 체크
                        queue.add(new Point(nr,nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }

        return cnt;
    }
}

class Point {
    int r;
    int c;

    Point(int r, int c){
        this.r = r;
        this.c = c;
    }
}
