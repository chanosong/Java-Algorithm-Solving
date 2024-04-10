package Baekjoon.bj19238;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/19238
// 스타트 택시

public class bj19238 {

    static int n;
    static int m;
    static int fuel;
    static int[][] map;
    static int[][] guestInfo;
    static boolean[] guestStatus;
    // 행번호가 작은 -> 열번호가 작은
    static int[] dr = {-1,0,0,1};
    static int[] dc = {0,-1,1,0};
    static Map<String, Integer> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        fuel = Integer.parseInt(input[2]);

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        input = br.readLine().split(" ");

        // 현재 택시
        Taxi taxi = new Taxi(Integer.parseInt(input[0]) - 1,Integer.parseInt(input[1]) - 1, fuel, 0);

        guestInfo = new int[m][4];
        guestStatus = new boolean[m];

        for (int i = 0; i < m; i++) {
            guestInfo[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 시작점 승객의 인덱스 저장
        for (int i = 0; i < m; i++) {
            hashMap.put((guestInfo[i][0] - 1) + " "  + (guestInfo[i][1] - 1), i);
        }

        int[] start = new int[2];
        int[] end = new int[2];

        int finishNum = 0;

        while (finishNum < m) {
            // 다음 승객 찾기
            taxi = getGuestNum(taxi);

            // 다음 승객을 못채운다면 종료
            if (taxi.fuel < 0) break;

            // 승객 탑승 처리
            int idx = hashMap.remove(taxi.r + " " +  taxi.c);

            start[0] = taxi.r;
            start[1] = taxi.c;

            // 승객 도착지까지 운전
            taxi = goToDestination(taxi, guestInfo[idx][2] - 1, guestInfo[idx][3] - 1);

            // 승객을 도착지까지 못데려다 준다면 종료
            if (taxi.fuel < 0) break;

            end[0] = taxi.r;
            end[1] = taxi.c;

            // 연료 충전
            taxi.chargeFuel();
            finishNum++;
        }

        System.out.println(taxi.fuel);
    }

    // 현재 시점에서 가장 가까운 손님의 번호 반환
    static Taxi getGuestNum(Taxi taxi) {

        Taxi ret = new Taxi(-1,-1,-1,-1);

        Queue<Taxi> q = new LinkedList<>();
        q.add(new Taxi(taxi.r, taxi.c, taxi.fuel, taxi.use));

        boolean[][] visited = new boolean[n][n];
        visited[taxi.r][taxi.c] = true;

        while(!q.isEmpty()) {
            Taxi now = q.poll();

            // 아직 안 탄 승객인 기존 후보군과 비교
            if(hashMap.containsKey(now.r + " " + now.c)) {
                if (ret.use == - 1 || ret.use > now.use) ret = now;
                else if (ret.use == now.use){
                    if (ret.r > now.r) ret = now;
                    else if (ret.r == now.r) {
                        if (ret.c > now.c) ret = now;
                    }
                }
                else continue;
            }

            // 승객이 아닌 경우 탐색 지속
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                int nf = now.fuel - 1;
                int nu = now.use + 1;

                // 벗어난 경우 스킵
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                // 이미 방문하였거나 벽인 경우 스킵
                if (visited[nr][nc] || map[nr][nc] == 1) continue;

                // 연료가 바닥난 경우 스킵
                if (nf < 0) continue;

                // 현재 최소기록 있으면서 그것보다 더 많이 가는 경우엔 스킵
                if (ret.use > 0 && nu > ret.use) continue;

                q.add(new Taxi(nr,nc,nf, nu));
                visited[nr][nc] = true;
            }
        }

        // 찾지 못한 경우 스킵
        return ret;
    }

    // 승객을 도착지까지 운반
    static Taxi goToDestination(Taxi taxi, int er, int ec) {

        Queue<Taxi> q = new LinkedList<>();
        q.add(new Taxi(taxi.r, taxi.c, taxi.fuel, 0));

        boolean[][] visited = new boolean[n][n];
        visited[taxi.r][taxi.c] = true;

        while(!q.isEmpty()) {
            Taxi now = q.poll();

            // 승객인 경우 반환
            if(now.r == er && now.c == ec) return now;

            // 승객이 아닌 경우 탐색 지속
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                int nf = now.fuel - 1;
                int nu = now.use + 1;

                // 벗어난 경우 스킵
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                // 이미 방문하였거나 벽인 경우 스킵
                if (visited[nr][nc] || map[nr][nc] == 1) continue;

                // 연료가 바닥난 경우 스킵
                if (nf < 0) continue;

                q.add(new Taxi(nr,nc,nf, nu));
                visited[nr][nc] = true;
            }
        }

        return new Taxi(-1, -1, -1, - 1);
    }
}

class Taxi {
    int r;
    int c;
    int fuel;
    int use;

    Taxi(int r, int c , int fuel, int use) {
        this.r = r;
        this.c = c;
        this.fuel = fuel;
        this.use = use;
    }

    public void chargeFuel() {
        fuel += use * 2;
        use = 0;
    }

    public String toString() {
        return r + " " + c + " " + fuel + " " + use;
    }
}
