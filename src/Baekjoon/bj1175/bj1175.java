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
    static int[][][][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = -1;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new String[n][m];
        
        // x좌표, y좌표, 진입방향, C방문여부, D방문여부
        visited = new int[n][m][4][2][2];

        // 5차원 배열
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 2; l++) {
                        for (int m2 = 0; m2 < 2; m2++) {
                            visited[i][j][k][l][m2] = Integer.MAX_VALUE;
                        }
                    }
                }
            }
        }

        // 시작점 및 C와 D 설정
        int startX = 0, startY = 0;
        boolean isfoundedC = false;
        
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().split("");
            
            for (int j = 0; j < m; j++) {
                if (map[i][j].equals("S")) {
                    startX = i;
                    startY = j;
                }
                else if (map[i][j].equals("C")) {
                    // 두번째 C인 경우 D로 변경
                    if (isfoundedC) map[i][j] = "D";
                    isfoundedC = true;
                }
            }
        }

        Queue<Trace> queue = new LinkedList<>();
        queue.add(new Trace(startX,startY));

        while (!queue.isEmpty()) {
            Trace trace = queue.poll();

            // C좌표인 경우
            if (map[trace.x][trace.y].equals("C")) {
                trace.isVisitedC = 1;
            }
            // D좌표인 경우
            else if (map[trace.x][trace.y].equals("D")) {
                trace.isVisitedD = 1;
            }

            // C와 D를 모두 방문한 경우 종료
            if (trace.isVisitedC == 1 && trace.isVisitedD == 1) {
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
                int nIsVisitedC = trace.isVisitedC;
                int nIsVisitedD = trace.isVisitedD;

                // 벗어난 경우 스킵
                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;

                // 가지못하는 경우 스킵
                if (map[nr][nc].equals("#")) continue;

                // 이미 간 패턴이면 스킵
                if (visited[nr][nc][nDir][nIsVisitedC][nIsVisitedD] <= nDistance) continue;

                queue.add(new Trace(nr, nc, nDistance, nDir, nIsVisitedC, nIsVisitedD));

                // visited 처리
                visited[nr][nc][nDir][nIsVisitedC][nIsVisitedD] = trace.distance;
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
    // 임의로 C와 D로 설정
    int isVisitedC;
    int isVisitedD;

    public Trace(int x, int y) {
        this.x = x;
        this.y = y;
        distance = 0;
        prevDir = -1;
        isVisitedC = 0;
        isVisitedD = 0;
    }

    public Trace(int x, int y, int distance, int prevDir, int isVisitedC, int isVisitedD) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.prevDir = prevDir;
        this.isVisitedC = isVisitedC;
        this.isVisitedD = isVisitedD;
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
