package Baekjoon.bj19236;
// https://www.acmicpc.net/problem/19236

import java.util.Arrays;
import java.util.Scanner;

public class bj19236 {
    
    // 팔방회전
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,-1,-1,-1,0,1,1,1};

    static Fish[][] map = new Fish[4][4];

    // 0 ~ 16, 상어의 좌표 저장
    static int[][] location = new int[17][2];

    // 상어
    static Shark shark;

    // 총 먹은 번호
    static int total = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 물고기 배치
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = new Fish(i,j,sc.nextInt(), sc.nextInt() - 1);
                location[4 * i + j] = new int[]{i,j};
            }
        }

        // 상어 넣어주기
        shark = new Shark();
        shark.eat(map[0][0]);

        location[16] = new int[]{0,0};

        while (true) {
            // 물고기들 이동
            for (int i = 0; i < 15; i++) {
                int fishX = location[i][0];
                int fishY = location[i][1];

                // 이미 죽은 물고기거나 움직일 수 없다면
                if (fishX == -1 || !canMove(map[fishX][fishY])) continue;
                switchLocation(map[fishX][fishY]);
            }

            // 상어이동
            //moveShark(location[16][0], location[16][1],newMap);
        }


        /*
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(map[i][j].order);
            }
            System.out.println();
        }

         */
    }

    static void moveShark(int x, int y, Fish[][] nowMap) {
        // 상어 위치 세팅
        location[16][0] = x;
        location[16][1] = y;

        // 원래 그 자리에 있던 물고기 사망처리
        location[map[x][y].order][0] = -1;
        location[map[x][y].order][1] = -1;

        // 상어 먹기
        shark.eat(map[x][y]);

        // 먹은 후 이동
        for (int i = 1; i <= 3; i++) {
            int nx = shark.x + dx[shark.x] * i;
            int ny = shark.y + dy[shark.y] * i;

            // 갈 수 없으면 스킵
            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[nx][ny] == null) continue;

            // 갈 수 있다면 이동
            Fish[][] newMap = mapClone(nowMap);

            moveShark(nx, ny, newMap);
        }
    }

    // 움직일 수 있는지 확인
    static boolean canMove(Fish fish) {

        // 팔방 확인하면서 한바퀴 돌고
        for (int i = 0; i <= 8; i++) {
            int nx = fish.x + dx[fish.dir];
            int ny = fish.y + dy[fish.dir];

            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[nx][ny].isShark) {
                fish.rotate();
                continue;
            }

            // 움직일 수 있는 곳이 있다면 true
            return true;
        }
        // 없다면 false 반환
        return false;
    }

    // 자리 변경
    static void switchLocation(Fish fish) {
        // 스위칭 전 저장
        int x = fish.x;
        int y = fish.y;
        int nx = fish.x + dx[fish.dir];
        int ny = fish.y + dy[fish.dir];

        // 비어있는 곳이라면 바로 스위칭
        if (map[nx][ny] == null) {
            map[nx][ny] = fish;
            map[x][y] = null;
        }
        // 비어있지 않은 곳이라면
        else {
            // 전달용 변수
            int to = map[nx][ny].order;
            int td = map[nx][ny].dir;

            // location swap
            int[] temp = location[map[nx][ny].order];
            location[map[nx][ny].order] = location[fish.order];
            location[fish.order] = temp;

            // order 변경
            map[nx][ny].order = fish.order;
            map[nx][ny].dir = fish.dir;

            fish.order = to;
            fish.dir = td;
        }
    }

    // map 복붙
    static Fish[][] mapClone(Fish[][] map) {
        Fish[][] newMap = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

}

class Fish {
    int x;
    int y;
    int order;
    int dir;

    boolean isShark;
    Fish(){
        isShark = false;
    }

    Fish(int x, int y, int order, int dir) {
        this.x = x;
        this.y = y;
        this.order = order;
        this.dir = dir;
        isShark = false;
    }

    void rotate() {
        dir = (dir + 1) % 8;
    }
}

class Shark extends Fish {
    Shark() {
        isShark = true;
    }

    Shark(int x, int y, int order, int dir) {
        this.x = x;
        this.y = y;
        this.order = order;
        this.dir = dir;
        isShark = true;
    }

    void eat(Fish fish) {
        this.x = fish.x;
        this.y = fish.y;
        this.order = 16;
        this.dir = fish.dir;
    }
}



