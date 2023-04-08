package Baekjoon.bj19237;

import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/19237

public class bj19237 {

    // 탐색 방향
    static int[] dx = {0,-1,1,0,0};
    static int[] dy = {0,0,0,-1,1};

    static int N;
    static int M;
    static int k;
    
    // 지도
    static Field[][] map;

    // 상어들 위치
    static int[][] loc;

    // 상어들이 생각하는 다음 위치
    static int[][] nextLoc;

    // 타이머
    static int time = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer;

        // 기본 정보 입력
        N = sc.nextInt();
        M = sc.nextInt();
        k = sc.nextInt();

        // Map 초기화
        map = new Field[N][N];
        loc = new int[M][2];
        nextLoc = new int[M][2];

        // 지도 저장 및 상어 생성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int tmp = sc.nextInt();
                map[i][j] = setField(tmp);
                
                // 상어인 경우 위치 저장
                if (tmp != 0) loc[tmp - 1] = new int[]{i, j};
            }
        }

        // 일단 복사
        for (int i = 0; i < M; i++) {
            nextLoc[i] = Arrays.copyOf(loc[i], loc[i].length);
        }

        // 각 상어 초기 방향 지정
        for (int i = 0; i < M; i++) {
            int[] pos = loc[i];
            map[pos[0]][pos[1]].livingShark.setNowDir(sc.nextInt());
        }

        // 버퍼 초기화
        sc.nextLine();

        // 각 상어 방향 우선순위 지정
        for (int i = 0; i < M; i++) {
            int[] pos = loc[i];
            for (int j = 0; j < 4; j++) {
                map[pos[0]][pos[1]].livingShark.setDirOrder(j, Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray());
            }
        }

        while(time <= 1000) {
            // 끝난지 확인
            if (isEnd()) {
                break;
            }

            // 끝나는 조건이 아니라면
            // 먼저 스캔
            decideShark();
            // 후 이동
            moveShark();
            // 냄새 빠짐
            smellsGone();
            //check(time);
            time++;
        }

        // 시간초과인 경우 처리
        if (time > 1000) answer = -1;
        else answer = time;

        System.out.println(answer);
    }

    static void check(int time) {
        System.out.println(time);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].livingShark == null) System.out.print("0 ");
                else System.out.print(map[i][j].livingShark.rank + " ");
            }
            System.out.println();
        }
    }

    static boolean isEnd() {
        for (int i = 1; i < loc.length; i++) {
            if (loc[i][0] != -1) return false;
        }
        return true;
    }

    static void smellsGone() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 상어가 없는 곳만 냄새 빼기
                if (map[i][j].livingShark == null) {
                    if (map[i][j].lastTime > 0) {
                        map[i][j].lastTime -= 1;
                        // 다 날라간 경우 초기화
                        if (map[i][j].lastTime == 0) {
                            map[i][j].smellNum = 0;
                        }
                    }
                }
            }
        }
    }

    // 현재 상황을 보고 각자 다음 이동 좌표를 계산
    static void decideShark() {
        // 상어 M마리에 대해 반복
        for (int i = 0; i < M; i++) {
            int[] sharkPos = loc[i];

            // 상어가 이미 죽은 상황이라면 스킵
            if (sharkPos[0] == -1) continue;

            // 현재 조작 할 상어
            Shark nowShark = map[sharkPos[0]][sharkPos[1]].livingShark;

            // 죽지 않은 경우 이동
            // 우선 순위에 따라 빈칸 이동
            boolean isMoved = false;
            for (int j = 0; j < 4; j++) {
                // 다음 번 이동 예정 필드 좌표
                int nx = sharkPos[0] + dx[nowShark.dirOrder[nowShark.nowDir - 1][j]];
                int ny = sharkPos[1] + dy[nowShark.dirOrder[nowShark.nowDir - 1][j]];

                // 배열을 벗어나는 경우 out
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                // 아무 냄새가 없는 곳이라면 바로 이동
                if (map[nx][ny].smellNum == 0) {

                    nextLoc[i][0] = nx;
                    nextLoc[i][1] = ny;
                    // 방향 적용
                    nowShark.nowDir = nowShark.dirOrder[nowShark.nowDir - 1][j];
                    // 움직였다는 표시
                    isMoved = true;
                    break;
                }
            }

            // 빈칸 이동 못한 경우 자신의 냄새로 돌아감
            if (isMoved) continue;

            for (int j = 0; j < 4; j++) {
                // 다음 번 이동 예정 필드 좌표
                int nx = sharkPos[0] + dx[nowShark.dirOrder[nowShark.nowDir - 1][j]];
                int ny = sharkPos[1] + dy[nowShark.dirOrder[nowShark.nowDir - 1][j]];

                // 배열을 벗어나는 경우 out
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                // 내 냄새가 나는 곳이라면 다음 타겟
                if (map[nx][ny].smellNum == nowShark.rank) {
                    nextLoc[i][0] = nx;
                    nextLoc[i][1] = ny;
                    // 방향 적용
                    nowShark.nowDir = nowShark.dirOrder[nowShark.nowDir - 1][j];

                    break;
                }
            }
        }
    }

    // 다음 이동 좌표로 실제 이동
    static void moveShark() {

        // 상어 M마리에 대해 반복
        for (int i = 0; i < M; i++) {
            int[] sharkPos = loc[i];

            // 상어가 이미 죽은 상황이라면 스킵
            if (sharkPos[0] == -1) continue;

            // 현재 조작 할 상어
            Shark nowShark = map[sharkPos[0]][sharkPos[1]].livingShark;
            
            // 죽지 않은 경우 이동
            // 다음 좌표 세팅
            int nx = nextLoc[i][0];
            int ny = nextLoc[i][1];

            // 그냥 갈 수 있는 경우
            if (map[nx][ny].livingShark == null) {
                // map에 적용
                // 빈 칸에 적용
                map[nx][ny].livingShark = nowShark;
                map[nx][ny].lastTime = k;
                map[nx][ny].smellNum = nowShark.rank;

                // 원래 있던 곳 비워두기
                map[loc[i][0]][loc[i][1]].livingShark = null;
                // map[loc[i][0]][loc[i][1]].lastTime -= 1;

                // loc에서 좌표 변경
                loc[i][0] = nx;
                loc[i][1] = ny;
                continue;
            }

            // 이미 가있는 상어가 있는 경우 -> 이 경우 무조건 죽음
            map[sharkPos[0]][sharkPos[1]].livingShark = null;
            // map[sharkPos[0]][sharkPos[1]].lastTime -= 1;

            // loc에서도 사망처리
            loc[i][0] = -1;
            loc[i][1] = -1;
        }
    }

    static Field setField(int x) {
        Field f = new Field();

        // 0인 경우 빈 필드 반환
        if (x == 0) return f;

        // 0이 아닌 경우 상어 넣어서 반환
        f.livingShark = new Shark(x);
        f.smellNum = x;
        f.lastTime = k;
        return f;
    }
}

class Field {
    int lastTime;
    int smellNum;
    Shark livingShark;

    Field() {
        lastTime = 0;
        smellNum = 0;
        livingShark = null;
    }
}

class Shark {
    int rank;
    int nowDir;
    int[][] dirOrder;

    Shark(int rank) {
        this.rank = rank;
        dirOrder = new int[4][4];
    }

    void setNowDir(int d) {
        nowDir = d;
    }

    void setDirOrder(int i, int[] dir) {
        dirOrder[i] = dir;
    }
}