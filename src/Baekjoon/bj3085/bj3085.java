package Baekjoon.bj3085;

// https://www.acmicpc.net/problem/3085
// 사탕 게임

import java.io.*;
import java.util.*;

public class bj3085 {
    static int n;
    static String[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new String[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().split("");
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 오른쪽과 바꿨을때
                if (j + 1 < n) {
                    answer = Math.max(answer, Math.max(checkVertical(i,j,map[i][j + 1]), checkVertical(i, j + 1, map[i][j])));
                    if (map[i][j].equals(map[i][j + 1])) answer = Math.max(answer, checkLeft(i,j,map[i][j+1]) + checkRight(i,j+1,map[i][j]));
                    else answer = Math.max(answer, Math.max(checkLeft(i,j,map[i][j+1]), checkRight(i,j+1,map[i][j])));
                }
                // 아래와 바꿨을 때
                if (i + 1 < n) {
                    if (map[i][j].equals(map[i + 1][j])) answer = Math.max(answer, checkUp(i,j,map[i + 1][j]) + checkDown(i + 1,j,map[i][j]));
                    else answer = Math.max(answer, Math.max(checkUp(i,j,map[i + 1][j]), checkDown(i + 1,j,map[i][j])));
                    answer = Math.max(answer, Math.max(checkHorizontal(i,j,map[i + 1][j]), checkHorizontal(i + 1, j, map[i][j])));
                }
            }
        }

        System.out.println(answer);
    }

    static int checkLeft(int r, int c, String type) {
        int ret = 1;

        for (int i = c - 1; i >= 0; i--) {
            if (map[r][i].equals(type)) ret++;
            else break;
        }

        return ret;
    }

    static int checkRight(int r, int c, String type) {
        int ret = 1;

        for (int i = c + 1; i < n; i++) {
            if (map[r][i].equals(type)) ret++;
            else break;
        }

        return ret;
    }

    static int checkUp(int r, int c, String type) {
        int ret = 1;

        for (int i = r - 1; i >= 0; i--) {
            if (map[i][c].equals(type)) ret++;
            else break;
        }

        return ret;
    }

    static int checkDown(int r, int c, String type) {
        int ret = 1;

        for (int i = r + 1; i < n; i++) {
            if (map[i][c].equals(type)) ret++;
            else break;
        }

        return ret;
    }

    static int checkVertical(int r, int c, String type) {
        Queue<Point> q = new LinkedList<>();

        Point start = new Point(r, c);
        q.add(start);
        int ret = 0;

        boolean[] visited = new boolean[n];

        while (!q.isEmpty()) {
            Point cur = q.poll();
            ret++;
            visited[cur.r] = true;

            if (cur.r + 1 < n && map[cur.r + 1][cur.c].equals(type)) {
                Point tmp = new Point(cur.r + 1, cur.c);
                if (!visited[cur.r + 1]) q.add(tmp);
            }

            if (cur.r - 1 >= 0 && map[cur.r - 1][cur.c].equals(type)) {
                Point tmp = new Point(cur.r - 1, cur.c);
                if (!visited[cur.r - 1]) q.add(tmp);
            }
        }

        return ret;
    }

    static int checkHorizontal(int r, int c, String type) {
        Queue<Point> q = new LinkedList<>();

        Point start = new Point(r, c);
        q.add(start);
        int ret = 0;

        boolean[] visited = new boolean[n];

        while (!q.isEmpty()) {
            Point cur = q.poll();
            ret++;
            visited[cur.c] = true;

            if (cur.c + 1 < n && map[cur.r][cur.c + 1].equals(type)) {
                Point tmp = new Point(cur.r, cur.c + 1);
                if (!visited[cur.c + 1]) q.add(tmp);
            }

            if (cur.c - 1 >= 0 && map[cur.r][cur.c - 1].equals(type)) {
                Point tmp = new Point(cur.r, cur.c - 1);
                if (!visited[cur.c - 1]) q.add(tmp);
            }
        }

        return ret;
    }
}

class Point implements Comparable<Point> {
    int r;
    int c;

    Point (int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Point o) {
        if (this.r == o.r && this.c == o.c) return 0;
        else return 1;
    }
}