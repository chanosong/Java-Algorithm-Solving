package Baekjoon.bj1726;

// https://www.acmicpc.net/problem/1726
// 1726 로봇

import java.io.*;
import java.util.*;

public class bj1726 {

    static int n,m;
    static int[][] map;
    static int[][][] minCnt;

    // 동 서 남 북
    static int[] dr = {0, 0, 0, 1, -1};
    static int[] dc = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        minCnt = new int[m][n][5];

        for (int i = 0; i < m; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 1; k < 5; k++) {
                    minCnt[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        Trace robot = new Trace(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), 0);

        st = new StringTokenizer(br.readLine());
        Trace finish = new Trace(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), Integer.MAX_VALUE);

        Queue<Trace> queue = new LinkedList<>();
        queue.add(robot);
        minCnt[robot.x][robot.y][robot.dir] = 0;

        while (!queue.isEmpty()) {
            Trace now = queue.poll();

            // 좌표가 동일한 경우 카운트 비교
            if (now.x == finish.x && now.y == finish.y) {
                // 방향도 틀린 경우
                if (now.dir != finish.dir) {
                    if (now.dir == 1 || now.dir == 2) {
                        if (finish.dir == 3 || finish.dir == 4) now.cmdCnt++;
                        else now.cmdCnt += 2;
                    }
                    else if (now.dir == 3 || now.dir == 4) {
                        if (finish.dir == 1 || finish.dir == 2) now.cmdCnt++;
                        else now.cmdCnt += 2;
                    }
                }

                finish.cmdCnt = Math.min(finish.cmdCnt, now.cmdCnt);
                continue;
            }
            
            // 좌표가 다른 경우 계속 진행
            for (int i = 1; i < 5; i++) {
                boolean keepGoing = true;

                for (int k = 1; k <= 3; k++) {
                    if (!keepGoing) break;

                    int nr = now.x + dr[i] * k;
                    int nc = now.y + dc[i] * k;
                    int nCnt = now.cmdCnt + 1;

                    // 방향이 다른 경우
                    if (now.dir != i) {
                        if (now.dir == 1 || now.dir == 2) {
                            if (i == 3 || i == 4) nCnt++;
                            else nCnt += 2;
                        }
                        else {
                            if (i == 1 || i == 2) nCnt++;
                            else nCnt += 2;
                        }
                    }

                    // 벗어나 거나 가지못하는 곳인 경우 스킵
                    if (nr < 0 || nc < 0 || nr >= m || nc >= n || map[nr][nc] == 1) {
                        keepGoing = false;
                        continue;
                    }
                    // 기존 숫자 이상인 경우 스킵
                    if (minCnt[nr][nc][i] <= nCnt) continue;

                    queue.add(new Trace(nr, nc, i, nCnt));
                    minCnt[nr][nc][i] = nCnt;
                }
            }
        }

        System.out.println(finish.cmdCnt);
    }
}

class Trace {
    int x;
    int y;
    int dir;
    int cmdCnt;

    Trace (int x, int y, int dir, int cmdCnt){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cmdCnt = cmdCnt;
    }
}
