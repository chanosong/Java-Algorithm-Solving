package Baekjoon.bj18290;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/18290
// 18290 NM과 K (1)

public class bj18290 {
    static int n, m, k;
    static int[][] map;
    static boolean[][] visited;

    static int answer = Integer.MIN_VALUE;

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 시작지점을 선택한 경우
        recursive(0,0,0, 0);

        System.out.println(answer);
    }

    static void recursive(int x, int y, int score ,int cnt) {
        if (cnt >= k) {
            answer = Math.max(answer, score);
        }
        else {
            for (int i = x; i < n; i++) {
                for (int j = y; j < m; j++) {
                    if (visited[i][j]) continue;
                    if (checkUsable(i,j)) {
                        visited[i][j] = true;
                        recursive(x,y,score+map[i][j],cnt+1);
                        visited[i][j] = false;
                    }
                }
            }
        }
    }

    static boolean checkUsable(int x, int y) {
        boolean useable = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dr[i];
            int ny = y + dc[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (visited[nx][ny]) useable = false;

        }

        return useable;
    }
}