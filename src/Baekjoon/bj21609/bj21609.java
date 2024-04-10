package Baekjoon.bj21609;

// https://www.acmicpc.net/problem/21609
// 21609 상어중학교

import java.io.*;
import java.util.*;

public class bj21609 {
    static int n;
    static int m;
    static int[][] map;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    static int score = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");

        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        while (true) {

//            System.out.println("Start --------");
//            printMap();

            List<Point> blockGroup = findGroup();

            if (blockGroup.isEmpty()) break;

            getScore(blockGroup);

//            System.out.println("Scoring --------");
//            printMap();

            gravity();

//            System.out.println("Gravity --------");
//            printMap();

            spin();

//            System.out.println("Spin --------");
//            printMap();

            gravity();


//            System.out.println("Gravity --------");
//            printMap();
//
//            System.out.println(score);
        }

        System.out.println(score);
    }

    static List<Point> findGroup() {
        // 가장 큰 그룹의 좌표들을 저장할 arr
        List<Point> bestGroup = new ArrayList<>();
        Point basePoint = new Point(-1,-1);
        int baseRainbow = 0;

        // 순회 및 BFS 진행
        boolean[][] used = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 검은색 혹은 무지개거나 이미 방문한 경우 스킵
                if (map[i][j] <= 0 || used[i][j]) continue;

                // BFS 진행
                int color = map[i][j];

                Queue<Point> q = new LinkedList<>();
                q.add(new Point(i,j));
                used[i][j] = true;

                // 좌표들과 기준블록을 저장할 변수
                List<Point> arr = new ArrayList<>();
                Point bp = new Point(n,n);
                int rainbowCnt = 0;

                while (!q.isEmpty()) {
                    Point now = q.poll();
                    arr.add(now);
                    if (map[now.r][now.c] == 0) rainbowCnt++;
                    else {
                        // 현재가 무지개블록이 아닌 경우에만 기준블록 재산정
                        bp = getBasePoint(bp, now);
                    }

                    for (int k = 0; k < 4; k++) {
                        int nr = now.r + dr[k];
                        int nc = now.c + dc[k];

                        // 벗어나는 경우 스킵
                        if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                        // 색이 다르거나, 검정색이거나, 빈칸이거나 이미 간 곳인 경우 스킵
                        if ((map[nr][nc] != color && map[nr][nc] > 0) || map[nr][nc] == -1 || map[nr][nc] == -2 || used[nr][nc]) continue;

                        // 나머지 경우 확장 가능
                        used[nr][nc] = true;
                        q.add(new Point(nr,nc));
                    }
                }

                // 새로운 그룹의 블록이 1개라면 미인정
                if (arr.size() < 2) {
                    continue;
                }

                // 무지개 블록의 경우 다시 갈 수 있게 변경
                for (Point p : arr) {
                    if (map[p.r][p.c] == 0) used[p.r][p.c] = false;
                }

                // 가장 큰 그룹인지 확인
                // 새로운 그룹이 사이즈가 더 큰 경우 변경
                if (bestGroup.size() < arr.size()) {
                    bestGroup = arr;
                    basePoint = bp;
                    baseRainbow = rainbowCnt;
                }
                // 사이즈가 같은 경우
                else if (bestGroup.size() == arr.size()) {
                    // 무지개 블록 개수 비교
                    if (baseRainbow < rainbowCnt) {
                        bestGroup = arr;
                        basePoint = bp;
                        baseRainbow = rainbowCnt;
                    }
                    else if (baseRainbow == rainbowCnt){
                        // 기준블록으로 비교 후 새로운 기준블록이 더 맞는 경우 변경
                        if (compareBasePoint(basePoint, bp).equals(bp)) {
                            bestGroup = arr;
                            basePoint = bp;
                            baseRainbow = rainbowCnt;
                        }
                    }
                }

                // System.out.println("Group +");
            }
        }

        return bestGroup;
    }

    static void printMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 더 작은 기준블록 반환
    static Point getBasePoint(Point x, Point y) {
        if (x.r < y.r) return x;
        else if (x.r == y.r) {
            if (x.c < y.c) return x;
            return y;
        }
        else return y;
    }

    // 더 큰 기준블록 반환
    static Point compareBasePoint(Point x, Point y) {
        if (x.r < y.r) return y;
        else if (x.r == y.r) {
            if (x.c < y.c) return y;
            return x;
        }
        else return x;
    }

    // 점수 계산하면서 빈칸 처리
    static void getScore(List<Point> arr) {
        // 점수 계산
        score += Math.pow(arr.size(), 2);

        // map 비우기 ( 빈칸 : -2 )
        for (Point p : arr) {
            map[p.r][p.c] = -2;
        }
    }

    // 중력 작용
    static void gravity() {
        for (int j = 0; j < n; j++) {
            // 아래서부터 확인
            for (int i = n - 1; i > 0; i--) {
                // 현재 기준 가장 가까운 색깔 블록 확인
                // 빈칸이 아닌 경우 스킵
                if (map[i][j] >= -1) continue;

                for (int k = i - 1; k >= 0; k--) {
                    // 검은색인 경우 더이상 불가능
                    if (map[k][j] == -1) break;

                    // 빈칸인 경우 스킵
                    if (map[k][j] == -2) continue;

                    // 움직일 수 있는 블록인 경우 스위치
                    map[i][j] = map[k][j];
                    map[k][j] = -2;
                    break;
                }
            }
        }
    }

    // 90도 회전
    static void spin() {
        int[][] newMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i][j] = map[j][(n - 1) - i];
            }
        }

        map = newMap;
    }
}

class Point {
    int r;
    int c;

    Point (int r, int c) {
        this.r = r;
        this.c = c;
    }
}