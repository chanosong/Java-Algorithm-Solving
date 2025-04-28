package Baekjoon.bj1175;

// https://www.acmicpc.net/problem/1175
// 1175 배달

import java.io.*;
import java.util.*;

public class bj1175 {

    // 우 상 좌 하
    static int[] dr = {0,-1,0,1};
    static int[] dc = {1,0,-1,0};

    static int n;
    static int m;

    static String[][] map;
    static int[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = -1;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new String[n][m];
        visited = new int[n][m][4];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        int startX = 0, startY = 0;

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().split("");

            for (int j = 0; j < m; j++) {
                if (map[i][j].equals("S")) {
                    startX = i;
                    startY = j;
                }
            }
        }

        Queue<Trace> queue = new LinkedList<>();
        queue.add(new Trace(startX,startY));

        while (!queue.isEmpty()) {
            Trace trace = queue.poll();

            if (trace.x == startX && trace.y == startY) {
                trace.visited.add(new Point(startX, startY));
            }

            if (trace.visited.size() == 2) {
                answer = trace.distance;
                break;
            }

            // 사방 확인
            for (int i = 0; i < 4; i++) {
                // 직전 움직인 방향은 제외
                if (trace.prevDir == i) continue;

                int nr = trace.x + dr[i];
                int nc = trace.y + dc[i];
                int nDir = i;
                int nDistance = trace.distance + 1;

                // 벗어난 경우 스킵
                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;

                // 가지못하는 경우 스킵
                if (map[nr][nc].equals("#")) continue;

                // 이미 간 패턴이면 스킵
                if (visited[nr][nc][nDir] <= nDistance) continue;

                queue.add(new Trace(nr, nc, nDistance, nDir, trace.visited));

                // visited 처리
                visited[trace.x][trace.y][i] = trace.distance;
            }
        }

        System.out.println(answer);
    }
}

class Trace {
    int x;
    int y;
    int prevDir;
    int distance;
    Set<Point> visited = new HashSet<>();

    public Trace(int x, int y) {
        this.x = x;
        this.y = y;
        distance = 0;
        prevDir = -1;
    }

    public Trace(int x, int y, int distance, int prevDir, Set<Point> visited) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.prevDir = prevDir;
        this.visited = visited;
    }
}

class Point implements Comparable<Point> {
    int x;
    int y;

    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Point o) {
        if (o.x == x && o.y == y) return 0;
        else return 1;
    }
}
