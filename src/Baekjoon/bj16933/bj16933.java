package Baekjoon.bj16933;

// 16933 벽 부수고 이동하기 3
// https://www.acmicpc.net/problem/16933

import java.io.*;
import java.util.*;

public class bj16933 {

    static int n;
    static int m;
    static int k;

    static int[][] map;
    static int[][][] visited;

    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        Queue<Trace> queue = new PriorityQueue<>();
        queue.add(new Trace(1,0,0, 0));
        visited[0][0][0] = 1;
        visited[0][0][1] = 0;

        while (!queue.isEmpty()) {
            Trace now = queue.poll();

            if (now.x == n - 1 && now.y == m - 1) {
                System.out.println(now.time);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = now.x + dr[i];
                int nc = now.y + dc[i];
                int nt = now.time + 1;

                // 범위를 벗어난 경우
                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                // 이미 갔으면서 더 빠른 순번이 있는 경우 스킵
                if (visited[nr][nc][0] > 0 && visited[nr][nc][0] < nt) continue;
                // 같은 순번인 경우 부순 횟수가 기존 이상이면 스킵 아니면 진행
                if (visited[nr][nc][0] == nt && visited[nr][nc][1] <= now.breakCnt) continue;
                // 벽을 만났는데 이미 벽을 다 부순 경우
                if (map[nr][nc] == 1 && k == now.breakCnt) continue;
                // 벽인 경우
                if (map[nr][nc] == 1) {
                    int tmpTime = nt;
                    // 현재 밤인 경우 기다리고 부수고 넘어가기
                    if (nt % 2 == 1)  tmpTime += 1;
                    queue.add(new Trace(tmpTime, nr, nc, now.breakCnt + 1));
                    visited[nr][nc][0] = tmpTime;
                    visited[nr][nc][1] = now.breakCnt + 1;
                    continue;
                }
                // 이동 가능한 경우
                queue.add(new Trace(nt, nr, nc, now.breakCnt));
                visited[nr][nc][0] = nt;
                visited[nr][nc][1] = now.breakCnt;
            }
        }

        System.out.println(-1);
    }
}

class Trace implements Comparable<Trace> {
    int time;
    int x;
    int y;
    int breakCnt;

    Trace (int time, int x, int y, int breakCnt) {
        this.time = time;
        this.x = x;
        this.y = y;
        this.breakCnt = breakCnt;
    }

    public int compareTo(Trace o) {
        return time - o.time;
    }
}
