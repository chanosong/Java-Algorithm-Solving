package Baekjoon.bj20057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj20057 {
    static int r;
    static int outSand = 0;
    static boolean end = false;
    static int nr;
    static int nc;

    static int[][] map;

    // 좌 하 우 상 순서
    static int[] dr = {0,1,0,-1};
    static int[] dc = {-1,0,1,0};

    // 좌우 바람 부는 경우, 가장 먼곳에서 차례로
    static int[] hr = {0,-1,1,-2,2,-1,1,-1,1,0};
    static int[] hc = {-2,-1,-1,0,0,0,0,1,1,-1};

    // 상하 바람 부는 경우, 가장 먼곳에서 차례로
    static int[] vr = {2,1,1,0,0,0,0,-1,-1,1};
    static int[] vc = {0,-1,1,-2,2,-1,1,-1,1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        r = Integer.parseInt(br.readLine());

        // map 입력
        map = new int[r][r];

        for (int i = 0; i < r; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 시작좌표
        nr = r / 2;
        nc = r / 2;

        // 이동 횟수
        int n = 0;

        // 시작지점부터 시작
        while (true) {
            // 종료
            if (nr == 0 && nc == 0) break;

            System.out.println(nr + ", " + nc);

            switch(n % 4) {
                case 0:
                    for (int i = 0; i < (n / 2) + 1; i++) {
                        leftWind();
                    }
                    break;
                case 1:
                    for (int i = 0; i < (n / 2) + 1; i++) {
                        downWind();
                    }
                    break;
                case 2:
                    for (int i = 0; i < (n / 2) + 1; i++) {
                        rightWind();
                    }
                    break;
                case 3:
                    for (int i = 0; i < (n / 2) + 1; i++) {
                        upWind();
                    }
            }
            n++;

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < r; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }

        System.out.println(outSand);
    }

    // 비어있는지 확인
    static boolean isEmpty(int nr, int nc) {
        if (map[nr][nc] == 0) return true;
        return false;
    }

    // 나갔는지 확인
    static boolean isOut(int nr, int nc) {
        if (nr < 0 || nr >= r || nc < 0 || nc >= r) return true;
        return false;
    }

    static void moveSand(int idx, String direction, int moved) {
        int tr, tc;

        if (direction.equals("Left")) {
            tr = nr + hr[idx];
            tc = nc + hc[idx];
        }
        else if (direction.equals("Right")){
            tr = nr - hr[idx];
            tc = nc - hc[idx];
        }
        else if (direction.equals("Down")) {
            tr = nr + vr[idx];
            tc = nc + vc[idx];
        }
        else {
            tr = nr - vr[idx];
            tc = nc - vc[idx];
        }

        // 나머지 처리하는 경우
        if (idx == 9) {
            if (!isOut(tr,tc)) {
                map[tr][tc] += map[nr][nc] - moved;
            }
            else {
                outSand += map[nr][nc] - moved;
            }

            map[nr][nc] = 0;
            return;
        }

        // 그 이외의 경우
        double coefficient;
        if (idx == 0) {
            coefficient = 0.05;
        }
        else if (idx >= 1 && idx <= 2) {
            coefficient = 0.1;
        }
        else if (idx >= 3 && idx <= 4) {
            coefficient = 0.02;
        }
        else if (idx >= 5 && idx <= 6) {
            coefficient = 0.07;
        }
        else {
            coefficient = 0.01;
        }

        moved += map[nr][nc] * coefficient;
        if (!isOut(tr,tc)) {
            map[tr][tc] += map[nr][nc] * coefficient;
        }
        else {
            outSand += map[nr][nc] * coefficient;
        }
    }

    static void leftWind() {
        if (isOut(nr, nc - 1)) return;

        // 나가지 않은 경우 진행
        nc -= 1;

        // 만일 비어있는 경우 조기 종료
        if (isEmpty(nr, nc)) return;

        // 비어있지 않은 경우 진행
        int moved = 0;

        for (int i = 0; i < 9; i++) {
            moveSand(i, "Left", moved);
        }
    }

    static void downWind() {
        if (isOut(nr + 1, nc)) return;

        // 나가지 않은 경우 진행
        nr += 1;

        // 만일 비어있는 경우 조기 종료
        if (isEmpty(nr, nc)) return;

        // 비어있지 않은 경우 진행
        int moved = 0;

        for (int i = 0; i < 9; i++) {
            moveSand(i, "Down", moved);
        }
    }

    static void rightWind() {
        if (isOut(nr, nc + 1)) return;

        // 나가지 않은 경우 진행
        nc += 1;

        // 만일 비어있는 경우 조기 종료
        if (isEmpty(nr, nc)) return;

        // 비어있지 않은 경우 진행
        int moved = 0;

        for (int i = 0; i < 9; i++) {
            moveSand(i, "Right", moved);
        }
    }

    static void upWind() {
        if (isOut(nr - 1, nc)) return;

        // 나가지 않은 경우 진행
        nr -= 1;

        // 만일 비어있는 경우 조기 종료
        if (isEmpty(nr, nc)) return;

        // 비어있지 않은 경우 진행
        int moved = 0;

        for (int i = 0; i < 9; i++) {
            moveSand(i, "Up", moved);
        }
    }
}
