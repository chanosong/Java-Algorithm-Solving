package Baekjoon.bj2234;

// https://www.acmicpc.net/problem/2234
// 2234 성곽

import java.io.*;
import java.util.*;

public class bj2234 {

    static int n, m;
    static int[][] map;
    static int[][] visited;

    // 서 북 동 남
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m][n];

        for (int i = 0; i < m; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        visited = new int[m][n];

        int idx = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Queue<Point> queue = new LinkedList<>();
                queue.add(new Point(i, j));
                visited[i][j] = idx;

                while (!queue.isEmpty()) {
                    Point p = queue.poll();

                    int nd = map[p.x][p.y];

                    for (int k = 0; k < 4; k++) {
                        int nx = p.x + dr[k];
                        int ny = p.y + dc[k];

                        int nnd = nd % 2;
                        nd /= 2;
                        
                        // 해당 방향이 막혀있는 경우 제외
                        if (nnd == 1) continue;
                    }
                }
            }
        }
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
