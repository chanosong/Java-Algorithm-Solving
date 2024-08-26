package Baekjoon.bj16957;

// 16957 체스판 위의 공
// https://www.acmicpc.net/problem/16957

import java.io.*;
import java.util.*;

public class bj16957 {

    static int r;
    static int c;
    static Panel[][] map;

    // 시계 방향
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < r; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < c; j++) {
                map[i][j] = new Panel(Integer.parseInt(st.nextToken()));
            }
        }

        boolean isMoved = true;

        // 이동 시작
        while(true) {
            // 이동이 없는 경우 종료
            if (!isMoved) break;

            Panel[][] temp = new Panel[r][c];

            for (int i = 0; i < r; i++) {
                temp[i] = map[i].clone();
            }

            // 순회 시작
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {

                    int dir = -1;
                    int min = map[i][j].min;

                    // 8방향 확인
                    for (int k = 0; k < 8; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];

                        // 벗어난 경우 스킵
                        if (nr < 0 || nc < 0 || nr >= r || nc >= c) continue;

                        // 값 비교
                        if (min > map[nr][nc].min) {

                        }
                    }
                }
            }
        }


    }
}

class Panel {
    int min;
    int cnt;

    Panel(int x) {
        min = x;
        cnt = 1;
    }
}
