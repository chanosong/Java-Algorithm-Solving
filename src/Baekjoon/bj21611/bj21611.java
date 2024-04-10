package Baekjoon.bj21611;

import java.io.*;
import java.util.*;


// https://www.acmicpc.net/problem/21611
// 마법사 상어와 블리자드

public class bj21611 {

    static int n;
    static int m;

    static int mid;

    static int[][] map;

    // 숫자 배열
    static Deque<Integer> deque;

    // left down right up 읽기용
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    // up down left right 블리자드용
    static int[] bdr = {-1,1,0,0};
    static int[] bdc = {0,0,-1,1};

    // 폭발한 1,2,3번 구슬의 개수
    static int[] bombCnt = {0,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        mid = n / 2;

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        deque = read();

        for (int i = 0; i < m; i++) {
            String[] cmd = br.readLine().split(" ");

            int d = Integer.parseInt(cmd[0]);
            int s = Integer.parseInt(cmd[1]);

            // 블리자드 실행
            blizzard(d - 1,s);

            // 빈칸 포함 읽어들이기
            deque = read();

            // 빈칸 당기기
            pour();

            // 다시 읽기
            deque = read();

            // 폭발 직전, 직후 구슬 개수
            int pastNum = deque.size();
            int nowNum = -1;
            
            // 폭발 전후가 같을때까지 진행
            while (true) {
                deque = explode();
            
                // 새로운 구슬 배열 사이즈
                nowNum = deque.size();

                // 더이상 변화가 없는 경우 탈출
                if (pastNum == nowNum) break;

                // 탈출 못한 경우 되돌리기
                pastNum = nowNum;
                nowNum = -1;
            }
            
            // 구슬의 변화
            deque = transform();

            // map에 다시 담기
            pour();
        }

        System.out.println(bombCnt[0] + bombCnt[1] * 2 + bombCnt[2] * 3);
    }

    // 정방향 구슬 읽기
    static Deque<Integer> read() {
        Deque<Integer> d = new ArrayDeque<>();

        // 구슬을 다 확인한 경우
        boolean isFinished = false;

        // 시작점 세팅
        int sr = mid;
        int sc = mid;

        int cycle = 1;

        while (sr >= 0) {

            if (isFinished) break;

            // 끝 마무리 처리
            if (sr == 0) {
                // 좌로만 이동
                for (int j = 0; j < (cycle - 1) * 2; j++) {
                    sr = sr + dr[0];
                    sc = sc + dc[0];

                    if (map[sr][sc] == 0) break;
                    else if (map[sr][sc] == -1) continue;
                    d.add(map[sr][sc]);
                }
                break;
            }
            else {
                // 좌 하
                for (int i = 0; i < 2; i++) {
                    // 바퀴수에 따른 이동 거리
                    for (int j = 0; j < cycle * 2 - 1; j++) {
                        sr = sr + dr[i];
                        sc = sc + dc[i];

                        if (map[sr][sc] == 0) {
                            isFinished = true;
                            break;
                        }
                        else if (map[sr][sc] == -1) continue;
                        d.add(map[sr][sc]);
                    }

                    if (isFinished) break;
                }

                if (isFinished) break;

                // 우 상
                for (int i = 2; i < 4; i++) {
                    // 바퀴수에 따른 이동 거리
                    for (int j = 0; j < cycle * 2; j++) {
                        sr = sr + dr[i];
                        sc = sc + dc[i];

                        if (map[sr][sc] == 0) {
                            isFinished = true;
                            break;
                        }
                        else if (map[sr][sc] == -1) continue;
                        d.add(map[sr][sc]);
                    }
                    if (isFinished) break;
                }
            }

            // 바퀴 수 증가
            cycle++;
        }

        return d;
    }

    // 블리자드
    static void blizzard(int d, int s) {
        int tr = mid;
        int tc = mid;

        for (int i = 0; i < s; i++) {
            tr += bdr[d];
            tc += bdc[d];

            // 얼음처리
            map[tr][tc] = -1;
        }
    }
    
    // Deque 순서대로 다시 채우기
    static void pour() {
        
        // 비우고 시작
        map = new int[n][n];
        
        // 시작점 세팅
        int sr = mid;
        int sc = mid;

        int cycle = 1;

        while (true) {
            // 다 채운 경우
            if (deque.isEmpty()) break;

            // 끝 마무리 처리
            if (sr == 0) {
                // 좌로만 이동
                for (int j = 0; j < (cycle - 1) * 2; j++) {
                    sr = sr + dr[0];
                    sc = sc + dc[0];

                    if (deque.isEmpty()) break;
                    map[sr][sc] = deque.poll();
                }
                break;
            }
            else {
                // 좌 하
                for (int i = 0; i < 2; i++) {
                    // 바퀴수에 따른 이동 거리
                    for (int j = 0; j < cycle * 2 - 1; j++) {
                        sr = sr + dr[i];
                        sc = sc + dc[i];

                        if (deque.isEmpty()) break;
                        map[sr][sc] = deque.poll();
                    }
                    if (deque.isEmpty()) break;
                }

                // 우 상
                for (int i = 2; i < 4; i++) {
                    // 바퀴수에 따른 이동 거리
                    for (int j = 0; j < cycle * 2; j++) {
                        sr = sr + dr[i];
                        sc = sc + dc[i];

                        if (deque.isEmpty()) break;
                        map[sr][sc] = deque.poll();
                    }
                    if (deque.isEmpty()) break;
                }
            }

            // 바퀴 수 증가
            cycle++;
        }
    }

    // 4개 이상 연속 구슬 폭발
    static Deque<Integer> explode() {
        Deque<Integer> temp = new ArrayDeque<>();

        int lastNum = -1;
        int streak = 0;
        
        while (!deque.isEmpty()) {
            int now = deque.poll();
            
            // 직전과 같은 숫자 구슬인 경우
            if (lastNum == now) streak++;
            // 직전과 다른 숫자 구슬인 경우
            else {
                // 직전 숫자가 4개 연속이었다면 pop 진행
                if (streak >= 4) {
                    for (int i = 0 ; i < streak; i++) {

                        int idx = temp.pollLast();
                        bombCnt[idx - 1]++;
                    }
                }
                lastNum = now;
                streak = 1;
            }

            temp.add(now);
        }

        // 마지막으로 확인
        if (streak >= 4) {
            for (int i = 0 ; i < streak; i++) {
                int idx = temp.pollLast();
                bombCnt[idx - 1]++;
            }
        }
        
        return temp;
    }

    // 구슬의 변화
    static Deque<Integer> transform() {
        Deque<Integer> temp = new ArrayDeque<>();

        if (deque.isEmpty()) return temp;

        int lastNum = deque.poll();
        int streak = 1;

        while (!deque.isEmpty()) {
            int now = deque.poll();

            // 직전과 같은 숫자 구슬인 경우
            if (lastNum == now) streak++;
            // 직전과 다른 숫자 구슬인 경우
            else {
                // 구슬의 개수, 구슬 번호 차례대로 삽입
                temp.add(streak);
                temp.add(lastNum);

                lastNum = now;
                streak = 1;
            }
        }

        // 마지막 그룹 후처리
        temp.add(streak);
        temp.add(lastNum);

        return temp;
    }
}
