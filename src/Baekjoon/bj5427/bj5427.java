package Baekjoon.bj5427;

// https://www.acmicpc.net/problem/5427
// 불

import java.util.*;

public class bj5427 {

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int w = sc.nextInt();
            int h = sc.nextInt();

            int startR = 0, startC = 0;

            sc.nextLine();

            String[][] map = new String[h][w];

            for (int j = 0; j < h; j++) {
                map[j] = sc.nextLine().split("");
            }

            Queue<int[]> firePoints = new LinkedList<>();

            for (int j = 0; j < h; j++) {
                for (int k = 0; k < w; k++) {
                    switch (map[j][k]) {
                        case "*":
                            firePoints.add(new int[]{j,k});
                            break;
                        case "@":
                            startR = j;
                            startC = k;
                    }
                }
            }

            int passedTime = start(map, new boolean[h][w], firePoints, startR, startC);

            if (passedTime == -1) {
                System.out.println("IMPOSSIBLE");
                continue;
            }
            System.out.println(passedTime + 1);
        }
    }

    static int start(String[][] map, boolean[][] visited, Queue<int[]> firePoints, int r, int c) {

        if (r == 0 || r == map.length - 1 || c == 0 || c == map[0].length) return 0;

        // 시작 지점 세팅
        visited[r][c] = true;
        Queue<Procedure> queue = new LinkedList<>();
        queue.add(new Procedure(r,c,0));

        // BFS 시작
        while (!queue.isEmpty()) {
            Queue<int[]> newFirePoints = new LinkedList<>();

            // 불이 먼저 번짐
            while (!firePoints.isEmpty()) {
                int[] now = firePoints.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = now[0] + dr[i];
                    int nc = now[1] + dc[i];

                    // 맵을 나간 경우 스킵
                    if (nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length) continue;

                    // 벽이거나 이미 탄 경우 스킵
                    if (map[nr][nc].equals("#") || map[nr][nc].equals("*")) continue;

                    map[nr][nc] = "*";
                    newFirePoints.add(new int[]{nr,nc});
                }
            }

            firePoints = newFirePoints;

            Queue<Procedure> newQueue = new LinkedList<>();

            while (!queue.isEmpty()) {
                Procedure now = queue.poll();

                // 탈출에 성공한 경우
                if (now.r == 0 || now.r == map.length - 1 || now.c == 0 || now.c == map[0].length - 1) return now.depth;

                // 계속 도전
                for (int i = 0; i < 4; i++) {
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];
                    int nd = now.depth + 1;

                    // 벽인 경우나 늦은 경우나 이미 간 경우 스킵
                    if (map[nr][nc].equals("#") || map[nr][nc].equals("*") || visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    newQueue.add(new Procedure(nr,nc,nd));
                }
            }

            queue = newQueue;
        }

        return -1;
    }

    /*
    static void printMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static void checkSafeZone(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals(".")) map[i][j] = String.valueOf(Integer.MAX_VALUE);
            }
        }
    }

    static int move(String[][] map, boolean[][] visited, int r, int c) {

        if (r == 0 || r == map.length - 1 || c == 0 || c == map[0].length) return 0;

        // 시작 지점 세팅
        visited[r][c] = true;
        Queue<Procedure> queue = new LinkedList<>();
        queue.add(new Procedure(r,c,0));

        // BFS 시작
        while (!queue.isEmpty()) {
            Procedure now = queue.poll();

            // 탈출에 성공한 경우
            if (now.r == 0 || now.r == map.length - 1 || now.c == 0 || now.c == map[0].length - 1) return now.depth;

            // 계속 도전
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                int nd = now.depth + 1;

                // 벽인 경우나 늦은 경우나 이미 간 경우 스킵
                if (map[nr][nc].equals("#") || visited[nr][nc] || (!map[nr][nc].equals(".") && Integer.parseInt(map[nr][nc]) <= nd)) continue;

                visited[nr][nc] = true;
                queue.add(new Procedure(nr,nc,nd));
            }
        }

        return -1;
    }

    // 번지는 함수
    static void burn(String[][] map) {
        int time = 0;
        List<int[]> points = new LinkedList<>();

        while (true) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    // 불이 아닌 경우 스킵
                    if (!map[i][j].equals("*")) continue;

                    map[i][j] = Integer.toString(time);
                    // 불인 경우 주변으로 번짐
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        // 범위를 벗어난 경우 스킵
                        if (nr < 0 || nr >= map.length || nc < 0 || nc >= map[i].length) continue;

                        // 옮겨 붙을 수 있는 곳이 아니면 스킵
                        if (!map[nr][nc].equals(".") && !map[nr][nc].equals("@")) continue;

                        // 불 번짐
                        points.add(new int[] {nr,nc});
                    }
                }
            }

            for (int[] point : points) {
                map[point[0]][point[1]] = "*";
            }

            // 시간 경과
            time++;

            // 더 이상 탈 수 없다면
            if (points.isEmpty()) break;
            // 비우기
            points.clear();
        }
    }

     */
}

class Procedure {
    int r;
    int c;
    int depth;

    Procedure(int r, int c, int depth) {
        this.r = r;
        this.c = c;
        this.depth = depth;
    }
}
