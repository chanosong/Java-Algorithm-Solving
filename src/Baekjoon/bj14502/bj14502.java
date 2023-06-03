package Baekjoon.bj14502;

// https://www.acmicpc.net/problem/14502
// 14502 연구소

import java.util.*;

public class bj14502 {
    static int n;
    static int m;

    static int max = 0;
    static int[][] map;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        // 버퍼 초기화
        sc.nextLine();

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // printMap();

        buildUp(-1, 0);

        System.out.println(max);
    }

    // 감염 시작
    static void spread() {
        int[][] newMap = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        // 새로운 맵 생성
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMap[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 바이러스가 아닌 경우 혹은 이미 방문한 곳인 경우 스킵
                if (newMap[i][j] != 2 || visited[i][j]) continue;

                // 바이러스이면서 처음 방문한 곳이라면 확장 시작
                visited[i][j] = true;
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{i,j});

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int nr = now[0] + dr[k];
                        int nc = now[1] + dc[k];

                        // 벗어난 경우 스킵
                        if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                        // 빈 칸이 아닌 경우, 이미 지나간 경우에도 스킵
                        if (newMap[nr][nc] != 0 || visited[nr][nc]) continue;

                        visited[nr][nc] = true;
                        newMap[nr][nc] = 2;
                        queue.add(new int[]{nr,nc});
                    }
                }
            }
        }

        max = Math.max(max, countSafeZone(newMap));
    }

    static int countSafeZone(int[][] map) {
        int cnt = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }

    static void buildUp(int start, int step) {
        // 3개를 고른 순간 상황 시작
        if (step == 3) {
            spread();
            return;
        }

        int startR = start / 10;
        int startC = start % 10;

        while (startR < n && startC < m) {

            // 커서 이동
            startC++;
            if (startC > m - 1) {
                startC = 0;
                startR++;
                if (startR > n - 1) break;
            }

            // 현재 상태
                int status = map[startR][startC];

            // 현재 상태가 빈 칸이 아닌 경우 스킵
            if (status != 0) continue;

            // 현재 상태가 빈 칸인 경우 벽 세우기
            map[startR][startC] = 1;
            buildUp(startR * 10 + startC, step + 1);
            map[startR][startC] = 0;
        }
    }

    static void printMap() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
