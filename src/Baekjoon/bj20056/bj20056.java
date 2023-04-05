package Baekjoon.bj20056;
// https://www.acmicpc.net/problem/20056
import java.util.*;

public class bj20056 {

    // 팔방 우선 순위 설정
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    static int[] dx = {-1,-1,0,1,1,1,0,-1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        sc.nextLine();
        // Map 생성
        Field[][] map = new Field[n][n];

        // 파이어 볼 입력
        for (int i = 0; i < m; i++) {
            int[] fbs = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            map[fbs[0] - 1][fbs[1] - 1] = new Field();
            map[fbs[0] - 1][fbs[1] - 1].add(new Fireball(fbs[2], fbs[3], fbs[4]));
        }

        // K 번 진행
        for (int i = 0; i < k; i++) {
            // 이동
            map = proceed(map);

            // 융합 후 분해
            intNDiv(map);
        }

        System.out.println(totalMass(map));
    }

    // 현재 맵의 파이어볼 총 질량
    static int totalMass (Field[][] map) {
        int total = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                // 없으면 스킵
                if (map[i][j] == null) continue;

                total += map[i][j].getFieldMass();
            }
        }

        return total;
    }

    static void intNDiv(Field[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                // 활성화가 안되었거나 파이어 볼이 하나도 없는 곳이면 스킵
                if (map[i][j] == null || map[i][j].list.isEmpty()) continue;

                // 있다면 합쳐지고 갈라지고 진행
                map[i][j].intnDiv();
            }
        }
    }

    static Field[][] proceed(Field[][] map) {
        Field[][] newMap = new Field[map.length][map.length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                // 활성화가 안되어 있거나 볼이 하나도 없는 곳이면 스킵
                if (map[i][j] == null || map[i][j].list.isEmpty()) continue;

                // 있다면 하나씩 이동
                for (Fireball f : map[i][j].list) {
                    // 넘어갈 경우 처리
                    int nx = (i + (dx[f.d] * f.s) % map.length) % map.length;
                    int ny = (j + (dy[f.d] * f.s) % map.length) % map.length;

                    if (nx < 0) nx += map.length;
                    if (ny < 0) ny += map.length;

                    if (newMap[nx][ny] == null) newMap[nx][ny] = new Field();
                    newMap[nx][ny].add(new Fireball(f.m,f.s,f.d));
                }
            }
        }

        return newMap;
    }
}

// 칸 단위
class Field {
    List<Fireball> list;

    Field() {
        list = new LinkedList<>();
    }

    int getFieldMass() {
        int total = 0;

        if (list.isEmpty()) return 0;

        for (Fireball f : list) {
            total += f.m;
        }

        return total;
    }

    void add(Fireball fireball) {
        list.add(fireball);
    }

    void intnDiv() {
        // 없거나 1개면 스킵
        if (list.size() <= 1) return;
        int tm = 0;
        int ts = 0;
        int td = 0;

        for (Fireball f : list) {
            tm += f.m;
            ts += f.s;

            // 홀짝이 일정하다면 절대값이 곧 길이
            if (f.d % 2 == 0) td += 1;
            else td -= 1;
        }

        int nm = (int) Math.floor(tm / 5);
        int ns = (int) Math.floor(ts / list.size());
        int nd;
        // 홀짝이 모두 같으면 0 2 4 6 아니라면 1 3 5 7
        if (Math.abs(td) == list.size()) nd = 0;
        else nd = 1;

        // 다 빼고 합쳐진거 새로 넣기
        list.clear();

        // 만약 질량이 0인 경우 비우고 종료
        if (nm == 0) return;

        // 모두 같았던 경우
        if (nd == 0) {
            for (int d = 0; d < 8; d += 2) {
                list.add(new Fireball(nm, ns, d));
            }
        }
        // 다른 방향이 있던 경우
        else {
            for (int d = 1; d < 8; d += 2) {
                list.add(new Fireball(nm, ns, d));
            }
        }
    }
}

class Fireball {
    int m;
    int s;
    int d;

    // 생성자
    Fireball(int m, int s, int d) {
        this.m = m;
        this.s = s;
        this.d = d;
    }
}
