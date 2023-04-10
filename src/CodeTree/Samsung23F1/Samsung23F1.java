package CodeTree.Samsung23F1;

import java.util.*;

public class Samsung23F1 {

    static int N;
    static int M;
    static int K;

    static Turret[][] map;

    // 우 하 좌 상, 나머지 모서리
    static int[] dx = {0,1,0,-1,1,1,-1,-1};
    static int[] dy = {1,0,-1,0,1,-1,1,-1};

    // 턴
    static int time;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // N, M, K 입력
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        // 버퍼 초기화
        sc.nextLine();
        // 초기화
        map = new Turret[N][M];

        // 포탑 배치
        for (int i = 0; i < N; i++) {
            int[] tp = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = new Turret(i,j,tp[j]);
            }
        }

        time = 1;

        // 초기 상태에서 혼자 남아있는 것인지 확인, 아닌 경우 진행
        if (!isLastTurret()){
            while (time <= K) {
                // 공격자 선정
                Turret attacker = selectAttacker();
                // 타겟 선정
                Turret target = selectTarget(attacker);
                // 경로 반환
                List<Turret> path = getPath(attacker, target);

                // 디버깅
                /*
                System.out.println(time);
                System.out.println("Attack :" + attacker.x + " " + attacker.y);
                System.out.println("Target :" + target.x + " " + target.y);


                for (Turret t : path) {
                    System.out.print("(" + t.x + " " + t.y + ") ");
                }
                System.out.println();
                */

                // 만일 경로가 시작점 하나라면 못가는 곳
                if (path.size() == 1) {
                    // System.out.println("Bomb");
                    bombAttack(attacker, target, path);
                }
                else {
                    // System.out.println("laser");
                    laserAttack(path);
                }
                // 정비
                maintenance(path);

                // 혼자 살아있는지 체크
                if (isLastTurret()) break;
                
                // showMap();

                time++;
            }
        }

        System.out.println(getStrongest());
    }
    // 현재 맵에서 제일 강한 포탑의 power 반환
    static int getStrongest() {
        int power = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                power = Math.max(power, map[i][j].power);
            }
        }

        return power;
    }

    // 현재 혼자 살아남아있는지 확인
    static boolean isLastTurret() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].power > 0) cnt++;
            }
        }

        return cnt == 1;
    }

    // 공격자, 피격자 이외는 정비
    static void maintenance(List<Turret> path) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Turret tmp = map[i][j];

                // 공격자거나 피격자인 경우 스킵
                if (path.contains(tmp)) continue;
                // 이미 파괴된 포탑이면 스킵
                if (tmp.power <= 0) continue;
                // 아닌 경우 power 증가
                tmp.power += 1;
            }
        }
    }

    // 경로 따라서 레이저 공격
    static void laserAttack(List<Turret> path) {
        int damage = path.get(0).power;
        int splashD = (int) Math.floor(damage / 2);

        // 중간 경로는 스플래쉬 데미지
        for (int i = 1; i < path.size() - 1; i++) {
            Turret midTarget = path.get(i);
            
            // 이미 파괴된 포탑이라면 스킵
            if (midTarget.power == 0) continue;
            // 살아있다면 타격
            midTarget.power -= splashD;
            // 음수로 내려갈 시 다시 0으로
            if (midTarget.power < 0) midTarget.power = 0;
        }

        // 타겟 타격
        Turret target = path.get(path.size() - 1);
        target.power -= damage;
        if (target.power < 0) target.power = 0;
    }
    
    // 타겟에 폭격
    static void bombAttack(Turret attacker, Turret target, List<Turret> path) {
        int damage = attacker.power;
        int splashD = (int) Math.floor(attacker.power / 2);

        // 주변 타격
        for (int i = 0; i < 8; i++) {
            int nx = target.x + dx[i];
            int ny = target.y + dy[i];

            // 넘어간 경우 처리
            if (nx < 0) nx = N - 1;
            if (nx >= N) nx = 0;
            if (ny < 0) ny = M - 1;
            if (ny >= M) ny = 0;

            // 이미 파괴 된 곳이라면 스킵
            if (map[nx][ny].power <= 0) continue;
            // 공격자 자기 자신은 피해 x
            if (map[nx][ny] == attacker) continue;

            // 공격
            Turret midTarget = map[nx][ny];
            midTarget.power -= splashD;
            if (midTarget.power < 0) midTarget.power = 0;
            // 경로에 추가
            path.add(midTarget);
        }

        // 기존 타겟 공격
        target.power -= damage;
        if (target.power < 0) target.power = 0;
        path.add(target);
    }

    // 타겟 선정
    static List<Turret> getPath(Turret attacker, Turret target) {
        // 시작 지점 세팅
        List<Turret> path = new LinkedList<>();
        path.add(attacker);

        // BFS 준비
        Queue<List<Turret>> queue = new LinkedList<>();
        queue.add(path);
        // visited
        boolean[][] visited = new boolean[N][M];
        visited[attacker.x][attacker.y] = true;

        while (!queue.isEmpty()) {
            List<Turret> now = queue.poll();
            Turret here = now.get(now.size() - 1);

            // 현재 위치가 타겟이라면 탈출
            if (here == target) {
                path = now;
                break;
            }

            // 우 하 좌 상으로 확인
            for (int i = 0; i < 4; i++) {
                int nx = here.x + dx[i];
                int ny = here.y + dy[i];

                // 벗어난 경우 처리
                if (nx < 0) nx = N - 1;
                if (nx >= N) nx = 0;
                if (ny < 0) ny = M - 1;
                if (ny >= M) ny = 0;

                // 만일 파괴된 포탑이라면 스킵
                if (map[nx][ny].power <= 0) continue;
                // 이미 간 곳이라면 스킵
                if (visited[nx][ny]) continue;

                // 다음 경로로 넣을 nPath 생성
                List<Turret> nPath = new LinkedList<>();
                
                for (Turret t: now) {
                    nPath.add(t);
                }

                nPath.add(map[nx][ny]);

                // 새로운 경로 삽입, 방문 표시
                queue.add(nPath);
                visited[nx][ny] = true;
            }

        }

        return path;
    }

    static Turret selectTarget(Turret attacker) {
        Turret target = new Turret(-1,-1,0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 만일 파괴된 포탑이거나 공격자일 경우 스킵
                if (map[i][j].power <= 0 || map[i][j] == attacker) continue;

                // 현재 지정된 타겟 보다 힘이 강한 경우 선택
                if (target.power < map[i][j].power) {
                    target = map[i][j];
                    continue;
                }

                // 현재 지정된 타겟과 힘이 같은 경우
                if (target.power == map[i][j].power) {
                    // 더 오래전에 버프를 받았던 경우
                    if (target.lastBuff > map[i][j].lastBuff) {
                        target = map[i][j];
                        continue;
                    }

                    // 버프 받았던 시각 또한 같다면
                    if (target.lastBuff == map[i][j].lastBuff) {
                        // 행과 열의 합이 더 작은 것으로
                        if (target.x + target.y > map[i][j].x + map[i][j].y) {
                            target = map[i][j];
                            continue;
                        }

                        // 행과 열의 합 또한 같다면 변경
                        if (target.x + target.y == map[i][j].x + map[i][j].y) {
                            // 열이 더 작은 것으로
                            if (target.y > map[i][j].y) {
                                target = map[i][j];
                            }
                        }
                    }
                }
            }
        }

        return target;
    }

    // 공격자 선정
    static Turret selectAttacker() {
        // 바뀔 더미용
        Turret attacker = new Turret(-1,-1, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 만일 파괴된 포탑이라면 스킵
                if (map[i][j].power <= 0) continue;

                // 현재 지정된 공격자보다 힘이 약한 경우 스위칭
                if (attacker.power > map[i][j].power) {
                    attacker = map[i][j];
                    continue;
                }

                // 현재 지정된 공격자와 힘이 같은 경우
                if (attacker.power == map[i][j].power) {
                    // 더 먼저 버프를 받았던 경우
                    if (attacker.lastBuff < map[i][j].lastBuff) {
                        attacker = map[i][j];
                        continue;
                    }

                    // 버프 받았던 시각 또한 같다면
                    if (attacker.lastBuff == map[i][j].lastBuff) {
                        // 행과 열의 합이 더 큰 것으로
                        if (attacker.x + attacker.y < map[i][j].x + map[i][j].y) {
                            attacker = map[i][j];
                            continue;
                        }

                        // 행과 열의 합 또한 같다면 변경
                        if (attacker.x + attacker.y == map[i][j].x + map[i][j].y) {
                            if (attacker.y < map[i][j].y) {
                                attacker = map[i][j];
                            }
                        }
                    }
                }
            }
        }

        // attacker에게 버프, 마지막 버프 받은 시각 갱신
        attacker.power += N + M;
        attacker.lastBuff = time;

        return attacker;
    }

    // 확인 용 맵 출력 함수
    static void showMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j].power + " ");
            }
            System.out.println();
        }
    }
}

class Turret {
    int x;
    int y;
    int power;
    int lastBuff;

    Turret(int x, int y, int power) {
        this.x = x;
        this.y = y;
        this.power = power;
        this.lastBuff = 0;
    }
}