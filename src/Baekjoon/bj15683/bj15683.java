package Baekjoon.bj15683;

// https://www.acmicpc.net/problem/15683
// 감시

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class bj15683 {

    static int n;
    static int m;

    // 동서남북
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};

    // 지도 정보
    static int[][] map;

    // 다시 확인해봐야할 좌표
    static List<int[]> checkList = new ArrayList<>();

    static int darksite = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 다시 방향 확인해야할 좌표 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] >= 1 && map[i][j] <= 5) checkList.add(new int[] {i,j});
            }
        }

        dfs(0);

        System.out.println(darksite);
    }

    static void dfs(int step) {
        if (step == checkList.size()) {
            darksite = Math.min(darksite, checkDarkSite(map));
            return;
        }

        int[] now = checkList.get(step);
        switch (map[now[0]][now[1]]) {
            case 1:
                for (int i = 1; i <= 4; i++) {
                    type1(now, i, -1);
                    dfs(step + 1);
                    type1(now, i, 1);
                }
                break;
            case 2:
                for (int i = 1; i <= 2; i++) {
                    type2(now, i, -1);
                    dfs(step + 1);
                    type2(now, i, 1);
                }
                break;
            case 3:
                for (int i = 1; i <= 4; i++) {
                    type3(now, i, -1);
                    dfs(step + 1);
                    type3(now, i, 1);
                }
                break;
            case 4:
                for (int i = 1; i <= 4; i++) {
                    type4(now, i, -1);
                    dfs(step + 1);
                    type4(now, i, 1);
                }
                break;
            case 5:
                type5(now, -1);
                dfs(step + 1);
                type5(now, 1);
        }
    }

    static int checkDarkSite(int[][] map) {
        int cnt = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }

    // cctv1의 경우, 1이면 끄기, -1이면 켜기
    static void type1(int[] point, int direction, int mode) {
        switch (direction) {
            case 1:
                checkEast(point, mode);
                break;
            case 2:
                checkWest(point, mode);
                break;
            case 3:
                checkSouth(point, mode);
                break;
            case 4:
                checkNorth(point, mode);
        }
    }

    // cctv2의 경우
    static void type2(int[] point, int direction, int mode) {
        switch (direction) {
            case 1:
                checkEast(point, mode);
                checkWest(point, mode);
                break;
            case 2:
                checkSouth(point, mode);
                checkNorth(point, mode);
        }
    }

    // cctv3의 경우
    static void type3(int[] point, int direction, int mode) {
        switch (direction) {
            case 1:
                checkEast(point, mode);
                checkNorth(point, mode);
                break;
            case 2:
                checkNorth(point, mode);
                checkWest(point, mode);
                break;
            case 3:
                checkWest(point, mode);
                checkSouth(point, mode);
                break;
            case 4:
                checkSouth(point, mode);
                checkEast(point, mode);
        }
    }

    // cctv4의 경우
    static void type4(int[] point, int direction, int mode) {
        if (direction != 1) checkSouth(point, mode);
        if (direction != 2) checkEast(point, mode);
        if (direction != 3) checkNorth(point, mode);
        if (direction != 4) checkWest(point, mode);
    }
    
    // cctv5의 경우
    static void type5(int[] point, int mode) {

        checkEast(point, mode);
        checkWest(point, mode);
        checkSouth(point, mode);
        checkNorth(point, mode);
    }

    static void checkEast(int[] point, int mode) {
        // 동쪽체크
        for (int i = 1; i < m - point[1]; i++) {
            int nr = point[0] + dr[0] * i;
            int nc = point[1] + dc[0] * i;

            if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 6) break;
            if (map[nr][nc] > 0) continue;
            map[nr][nc] += mode;
        }
    }

    static void checkWest(int[] point, int mode) {
        // 서쪽체크
        for (int i = 1; i < point[1] + 1; i++) {
            int nr = point[0] + dr[1] * i;
            int nc = point[1] + dc[1] * i;

            if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 6) break;
            if (map[nr][nc] > 0) continue;
            map[nr][nc] += mode;
        }
    }

    static void checkSouth(int[] point, int mode) {
        // 남쪽체크
        for (int i = 1; i < n - point[0]; i++) {
            int nr = point[0] + dr[2] * i;
            int nc = point[1] + dc[2] * i;

            if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 6) break;
            if (map[nr][nc] > 0) continue;
            map[nr][nc] += mode;
        }
    }

    static void checkNorth(int[] point, int mode) {
        // 북쪽체크
        for (int i = 1; i < point[0] + 1; i++) {
            int nr = point[0] + dr[3] * i;
            int nc = point[1] + dc[3] * i;

            if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 6) break;
            if (map[nr][nc] > 0) continue;
            map[nr][nc] += mode;
        }
    }
}
