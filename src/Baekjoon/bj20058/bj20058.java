package Baekjoon.bj20058;

import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/20058
// 마법사 상어와 파이어스톰

public class bj20058 {

    static int n;
    static int q;
    static int size;
    static int[][] map;
    static int[] l;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        q = Integer.parseInt(input[1]);

        size = (int) Math.pow(2,n);

        map = new int[size][size];

        for (int i = 0; i < size; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        l = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < q; i++) {

            System.out.println(l[i] + " 파이어볼");

            firestorm(l[i]);

            printMap();

            melt();

            printMap();
        }

        calculate();
    }

    static void printMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printNewMap(int[][] newMap) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(newMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // l단계 회전
    static void firestorm(int l) {

        // 0 이하인 경우 스킵
        if (l <= 0) return;

        // 파이어스톰 사이즈
        int length = (int) Math.pow(2,l);
        int s = length * length;

        // 영역 갯수
        int num = size * size / s;

        // 새로운 맵 생성
        int[][] newMap = new int[size][size];

        // 시작점 세팅
        int sr = 0;
        int sc = 0;
        int cnt = 1;

        while (cnt <= num) {
//            System.out.println(sr + " " + sc);
            /*
            // 영역이 홀수번 줄인 경우
            if (((cnt - 1) / (int) Math.sqrt(num)) % 2 == 0) {
                // 짝수번째 영역 그대로
                if (cnt % 2 == 0) {
                    copyMap(sr,sc,length,newMap);
                }
                // 홀수번째 영역인 경우 회전
                else {
                    rotateMap(sr,sc,length,newMap);
                }
            }
            // 영역이 짝수번 줄인 경우
            else {
                // 홀수번째 영역 그대로
                if (cnt % 2 != 0) {
                    copyMap(sr,sc,length,newMap);
                }
                // 짝수번째 영역인 경우 회전
                else {
                    rotateMap(sr,sc,length,newMap);
                }
            }
             */

            rotateMap(sr,sc,length,newMap);

            // 카운트 증가 후 시작점 이동
            cnt++;

            sc += (int) Math.pow(2,l);

            // 넘어간 경우 아랫줄로
            if (sc >= size) {
                sc = 0;
                sr += length;
            }
        }

        map = newMap;
    }

    // 그대로 복붙 영역
    // 시작점 r,c 사이즈 s, 복사 맵 newMap
    static void copyMap(int r, int c, int s, int[][] newMap) {
        for (int i = r; i < r + s; i++) {
            for (int j = c; j < c + s; j++) {
                newMap[i][j] = map[i][j];
            }
        }
    }

    // 회전 영역
    // 시작점 r,c 사이즈 s, 복사 맵 newMap
    static void rotateMap(int r, int c, int s, int[][] newMap) {
        int[][] part = new int[s][s];
        int[][] newPart = new int[s][s];

        // 돌릴 파트 복사
        for (int i = r; i < r + s; i++) {
            for (int j = c ; j < c + s; j++) {
                part[i - r][j - c] = map[i][j];
            }
        }

        // 돌리기
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                newPart[j][s - i - 1] = part[i][j];
            }
        }

        // 다시 끼워넣기
        for (int i = r; i < r + s; i++) {
            for (int j = c ; j < c + s; j++) {
                newMap[i][j] = newPart[i - r][j - c];
            }
        }
    }

    // 인접칸 확인하면서 녹이기
    static void melt() {
        int[][] newMap = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // 이미 얼음이 없는 경우 스킵
                if (map[i][j] == 0) continue;

                int cnt = 0;

                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];

                    // 벗어났거나 다 녹은 경우 스킵
                    if (nr < 0 || nr >= size || nc < 0 || nc >= size || map[nr][nc] <= 0) continue;
                    cnt++;
                }

                if (cnt < 3) newMap[i][j] = Math.max(map[i][j] - 1, 0);
                else newMap[i][j] = map[i][j];
            }
        }

        map = newMap;
    }

    // 얼음의 합, 가장 큰 덩어리 계산
    static void calculate() {
        boolean[][] visited = new boolean[size][size];
        int maxSize = 0;
        int total = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // 얼음이 없는 경우 스킵
                if (map[i][j] == 0) continue;

                total += map[i][j];
                // 갯수 카운터
                int tempSize = 0;

                // 얼음이 있는 경우 BFS 시작
                Queue<Point> q = new LinkedList<>();
                q.add(new Point(i,j));
                visited[i][j] = true;

                while (!q.isEmpty()) {
                    Point now = q.poll();

                    tempSize++;

                    for (int k = 0; k < 4; k++) {
                        int nr = now.r + dr[k];
                        int nc = now.c + dc[k];

                        // 벗어난 경우 스킵
                        if (nr < 0 || nr >= size || nc < 0 || nc >= size) continue;

                        // 이미 방문하였거나 얼음이 없는 경우도 스킵
                        if (map[nr][nc] == 0 || visited[nr][nc]) continue;

                        // 확장 가능한 경우 넣기
                        q.add(new Point(nr,nc));
                        visited[nr][nc] = true;
                    }
                }

                // 최대 사이즈 갱신
                maxSize = Math.max(maxSize, tempSize);
            }
        }

        // 정답 출력
        System.out.println(total);
        System.out.println(maxSize);
    }
}

class Point {
    int r;
    int c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
