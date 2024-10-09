package Baekjoon.bj16234;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/16234
// 16234 인구 이동

public class bj16234 {

    static int n, l, r;
    static int[][] map;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        n = input[0];
        l = input[1];
        r = input[2];

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int day = 0;

        while (true) {
            // BFS 진행하며 국경 열기
            int[][] visited = new int[n][n];
            int unionNum = 1;

            List<Integer> avgNum = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 이미 유니온에 속한 경우 스킵
                    if (visited[i][j] != 0) continue;
                    
                    // 그렇지 않은 경우 확장
                    visited[i][j] = unionNum;

                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[] {i,j});

                    int countryCnt = 0;
                    int peopleCnt = 0;

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();

                        countryCnt++;
                        peopleCnt += map[cur[0]][cur[1]];

                        for (int k = 0; k < 4; k++) {
                            int nr = cur[0] + dr[k];
                            int nc = cur[1] + dc[k];

                            // 벗어난 경우 스킵
                            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

                            // 이미 다른 대륙인 경우 스킵
                            if (visited[nr][nc] != 0) continue;

                            // 인구수 차이가 만족하지 않는 경우 스킵
                            int gap = Math.abs(map[cur[0]][cur[1]] - map[nr][nc]);
                            if (gap < l || gap > r) continue;

                            visited[nr][nc] = unionNum;

                            q.add(new int[] {nr,nc});
                        }
                    }

                    // 개수 증가
                    unionNum++;

                    // 정보 저장
                    avgNum.add((int) Math.floor((double) peopleCnt / countryCnt));
                }
            }

            // 국경이 열리지 않은 경우 종료
            if (unionNum == n * n + 1) break;

            day++;

            // 인원 분배
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = avgNum.get(visited[i][j] - 1);
                }
            }
            
            // 단 하나의 연합인 경우 종료
            if (unionNum == 2) break;
        }

        System.out.println(day);
    }
}
