package Baekjoon.bj23290;

// https://www.acmicpc.net/problem/23290
// 23290 마법사 상어와 복제

import java.util.*;
import java.io.*;

public class bj23290 {
    static int m;
    static int s;

    static int time = 0;

    // 실제 움직이는 맵과 복제 저장용 맵
    static Place[][] map = new Place[4][4];
    static Place[][] dmap = new Place[4][4];

    // 9시 10시 12시 1시 3시 5시 6시 7시
    static int[] dr = {0,-1,-1,-1,0,1,1,1};
    static int[] dc = {-1,-1,0,1,1,1,0,-1};


    // 상어용 상 좌 하 우
    static int[] sdr = {-1,0,1,0};
    static int[] sdc = {0,-1,0,1};

    static int sr;
    static int sc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        m = Integer.parseInt(input[0]);
        s = Integer.parseInt(input[1]);

        // map 활성화
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = new Place();
                dmap[i][j] = new Place();
            }
        }

        // 물고기 배치
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");

            int nr = Integer.parseInt(input[0]);
            int nc = Integer.parseInt(input[1]);
            int nd = Integer.parseInt(input[2]);

            map[nr - 1][nc - 1].fishList.add(new Fish(nr - 1,nc - 1,nd - 1));
        }

        
        // 상어 배치
        input = br.readLine().split(" ");

        sr = Integer.parseInt(input[0]) - 1;
        sc = Integer.parseInt(input[1]) - 1;

        map[sr][sc].isShark = true;

        // 연습 시작
        for (int i = 0; i < s; i++) {
            // 복제
            duplicate();

            // 이동
            moveFish();

            // 상어 이동
            moveShark();

            // 복제 물고기 생성
            duplicateComplete();
            
            // 시간 증가
            time++;
        }

        countAlive();
    }

    // 살아있는 물고기 개수 카운팅
    static void countAlive() {
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Place now = map[i][j];

                cnt += now.fishList.size();
            }
        }

        System.out.println(cnt);
    }

    // 복제 완료
    static void duplicateComplete() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Place now = map[i][j];

                now.fishList.addAll(dmap[i][j].fishList);
                dmap[i][j].fishList.clear();
            }
        }
    }

    // 복제 리스트에 삽입
    static void duplicate() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Place now = map[i][j];

                dmap[i][j].fishList.addAll(now.fishList);
            }
        }
    }

    // 물고기 이동
    static void moveFish() {
        // 임시 저장용 map
        Place[][] temp = new Place[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = new Place();
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Place now = map[i][j];

                // 기존 정보 가져오기
                temp[i][j].isShark = now.isShark;
                temp[i][j].fishSmellLimit = now.fishSmellLimit;

                // 움직일 물고기가 없는 경우 스킵
                if (now.fishList.isEmpty()) continue;

                // 움직일 물고기가 있는 경우 이동
                for (int k = 0; k < now.fishList.size(); k++) {
                    Fish nowFish = now.fishList.get(k);

                    boolean isMoved = false;

                    // 반시계 방향으로 돌면서 이동 가능한지 확인
                    for (int dd = 0; dd < 8; dd++ ) {
                        int nd = nowFish.d - dd;

                        // 음수 조정
                        if (nd < 0) nd += 8;

                        int nr = nowFish.r + dr[nd];
                        int nc = nowFish.c + dc[nd];

                        // 맵을 벗어나는 경우 스킵
                        if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;

                        // 상어가 있는 경우, 물고기 냄새가 남아있는 경우 스킵
                        if (map[nr][nc].isShark || map[nr][nc].fishSmellLimit >= time) continue;

                        // 이동
                        temp[nr][nc].fishList.add(new Fish(nr,nc,nd));
                        isMoved = true;
                        break;
                    }

                    // 만약 움직이지 못한 경우 그자리 그대로
                    if (!isMoved) {
                        temp[nowFish.r][nowFish.c].fishList.add(nowFish);
                    }
                }
            }
        }

        // 이동이 모두 끝난 후 기존 map과 바꿔치기
        map = temp;
    }

    // 상어 이동
    static void moveShark() {

        // 비교군 설정
        HuntSequence bestSeq = new HuntSequence(0,0,0,-1);
        bestSeq.history.append(555);

        // DFS 진행
        Stack<HuntSequence> st = new Stack<>();
        st.push(new HuntSequence(sr,sc,0,0));

        while (!st.isEmpty()) {
            HuntSequence seq = st.pop();

            // 3 스텝 다 된 경우 비교군과 비교
            if (seq.step == 3) {
                // 잡은 물고기의 수가 더 많은 경우
                if (seq.fishCnt > bestSeq.fishCnt) bestSeq = seq;
                else if (seq.fishCnt == bestSeq.fishCnt) {
                    // 잡은 숫자는 같지만 사전적으로 더 앞선 경우
                    if (seq.history.toString().compareTo(bestSeq.history.toString()) < 0) {
                        bestSeq = seq;
                    }
                }
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = seq.r + sdr[i];
                int nc = seq.c + sdc[i];
                int ns = seq.step + 1;

                StringBuffer nh = new StringBuffer(seq.history);
                nh.append(i + 1);

                // 벗어나는 경우 스킵
                if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;

                boolean isVisited = false;

                // 이미 간 곳인 경우 체크
                for (int j = 0; j < ns - 1; j++) {
                    if (seq.route[j][0] == nr && seq.route[j][1] == nc) {
                        isVisited = true;
                        break;
                    }
                }

                // 물고기 잡은 횟수
                int nfc = seq.fishCnt;

                // 첫 방문한 경우에만 잡은 물고기의 수 증가
                if (!isVisited) nfc += map[nr][nc].fishList.size();

                // 이동 경로 저장
                int[][] nroute = new int[3][2];

                for (int j = 0; j < ns - 1; j++) {
                    nroute[j][0] = seq.route[j][0];
                    nroute[j][1] = seq.route[j][1];
                }

                nroute[ns - 1][0] = nr;
                nroute[ns - 1][1] = nc;
                // 이동하면서 물고기 학살
                HuntSequence nextSeq = new HuntSequence(nr,nc,ns,nfc, nh, nroute);

                st.push(nextSeq);
            }
        }

//        System.out.println("잡은 물고기:" + bestSeq.fishCnt);
//        System.out.println("이동 방향: " + bestSeq.history.toString());

        // 가장 최선의 방식을 진행을 이행
        eat(bestSeq);
    }

    static void eat(HuntSequence seq) {
        
        // 기존에 상어자리 비우기
        map[sr][sc].isShark = false;

        // 상어 움직이기
        sr = seq.r;
        sc = seq.c;

        // 중간 물고기들 모두 죽이면서 물고기 냄새 세팅
        for (int i = 0; i < 3; i++) {
            int dr = seq.route[i][0];
            int dc = seq.route[i][1];

            // 빈 경우 스킵
            if (map[dr][dc].fishList.isEmpty()) continue;

            // 비지 않은 경우 fishList 비우고, 냄새 타이머
            map[dr][dc].fishList.clear();
            map[dr][dc].fishSmellLimit = time + 2;
        }

        map[sr][sc].isShark = true;
    }
}

class HuntSequence {
    int r;
    int c;
    int step;
    int fishCnt;
    StringBuffer history;
    int[][] route = new int[3][2];

    HuntSequence (int r, int c, int step, int fishCnt) {
        this.r = r;
        this.c = c;
        this.step = step;
        this.fishCnt = fishCnt;
        this.history = new StringBuffer();
    }

    HuntSequence (int r, int c, int step, int fishCnt, StringBuffer history, int[][] route) {
        this.r = r;
        this.c = c;
        this.step = step;
        this.fishCnt = fishCnt;
        this.history = history;
        this.route = route;
    }
}

class Fish {
    int r;
    int c;
    int d;

    Fish(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

class Shark {
    int r;
    int c;
    int d;

    Shark(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

class Place {
    List<Fish> fishList = new ArrayList<>();
    boolean isShark = false;
    int fishSmellLimit = -1;
}
