package Baekjoon.bj6087;

// https://www.acmicpc.net/problem/6087
// 6087 레이저 통신 (73퍼 오답)

import java.io.*;
import java.util.*;

public class bj6087 {

    static int w;
    static int h;

    static String[][] map;
    static int[][] mirrorCnt;

    // 상 좌 하 우
    static int[] dw = {-1, 0, 1, 0};
    static int[] dh = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new String[w][h];
        mirrorCnt = new int[w][h];

        for (int i = 0; i < w; i++) {
            Arrays.fill(mirrorCnt[i], Integer.MAX_VALUE);
        }

        int sw = 0;
        int sh = 0;

        for (int i = 0; i < w; i++) {
            map[i] = br.readLine().split("");

            for (int j = 0; j < h; j++) {
                if (map[i][j].equals("C")) {
                    sw = i;
                    sh = j;
                }
            }
        }

        // 시작지점 세팅
        mirrorCnt[sw][sh] = 0;

        // 시작 C에서 4가지 방향으로 진행
        for (int i = 0; i < 4; i++) {
            Queue<Procedure> q = new LinkedList<>();

            q.add(new Procedure(sw, sh, 0, i));

            int cycle = 0;
            
            while (!q.isEmpty()) {
                Procedure p = q.poll();

                // 정방향 진행
                shootLazer(p, p.direction, q);

                // 두번째 사이클부터 진행
                if (cycle > 0) {
                    // 우로 90도
                    shootLazer(p, (p.direction - 1 < 0) ? 3 : p.direction - 1, q);

                    // 좌로 90도
                    shootLazer(p, (p.direction + 1 > 3) ? 0 : p.direction + 1, q);
                }

                cycle++;
            }
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                // System.out.println(mirrorCnt[i][j]);

                if (map[i][j].equals("C") && !(i == sw && j == sh)) {
                    System.out.println(mirrorCnt[i][j]);
                }
            }
        }
    }

    static void shootLazer (Procedure p, int direction, Queue<Procedure> q) {
        int coefficient = 1;

        while (true) {
            int nw = p.w + dw[direction] * coefficient;
            int nh = p.h + dh[direction] * coefficient;
            int nCnt = (p.direction == direction) ? p.cnt : p.cnt + 1;

            // map 범위를 벗어난 경우 
            if (nw < 0 || nw >= w || nh < 0 || nh >= h) break;
            // 벽을 만난 경우
            if (map[nw][nh].equals("*")) break;
            // 기존 방식이 더 적은 거울을 사용하는 경우
            if (mirrorCnt[nw][nh] <= nCnt) break;

            mirrorCnt[nw][nh] = nCnt;

            // 목적지인 경우 해당 시퀀스 스킵
            if (map[nw][nh].equals("C")) break;

            q.add(new Procedure(nw, nh, nCnt, direction));

            coefficient++;
        }
    }
}

class Procedure {
    int w;
    int h;
    int cnt;
    int direction;

    Procedure (int w, int h, int cnt, int direction) {
        this.w = w;
        this.h = h;
        this.cnt = cnt;
        this.direction = direction;
    }
}