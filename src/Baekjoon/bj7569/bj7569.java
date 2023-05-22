package Baekjoon.bj7569;

// https://www.acmicpc.net/problem/7569
// 토마토

import java.util.*;

public class bj7569 {

    static int n;
    static int m;
    static int h;
    static int[][][] tomatoes;

    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};
    static int[] dh = {1,-1};

    // 익은 토마토가 들어있는 좌표
    static Set<Point> checkList = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        h = sc.nextInt();

        tomatoes = new int[h][m][n];

        // 버퍼 초기화
        sc.nextLine();

        int unripen = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < m; j++) {
                tomatoes[i][j] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int k = 0; k < n; k++) {
                    switch (tomatoes[i][j][k]) {
                        case 1:
                            checkList.add(new Point(i,j,k));
                            break;
                        case 0:
                            unripen++;
                    }
                }
            }
        }

        // 모든 토마토가 익은 상황
        if (unripen == 0) {
            System.out.println(0);
            return;
        }

        int time = 0;

        // 아직 익어야할 토마토가 있는 경우
        while (true) {
            // 익기
            ripen();
            // 시간 경과
            time++;

            // 새롭게 익은 토마토가 없는 경우
            if (checkList.size() == 0) break;
            
            // 남은 익어야할 토마토 개수 갱신
            unripen -= checkList.size();

            // 더 이상 익을 토마토가 없는 경우
            if (unripen == 0) break;
        }

        // 더 익을 토마토가 없는 경우 시간을, 있는 경우 -1 출력
        if (unripen == 0) System.out.println(time);
        else System.out.println(-1);
    }

    // 순회하며 토마토 익기
    static void ripen() {

        // 새롭게 익게 될 좌표
        Set<Point> newList = new HashSet<>();

        // 익은 구역만 확인
        for (Point point : checkList) {
            int i = point.x;
            int j = point.y;
            int k = point.z;

            for (int ii = 0; ii < 4; ii++) {
                int nr = j + dr[ii];
                int nc = k + dc[ii];

                // 범위 벗어난 경우 스킵
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                if (tomatoes[i][nr][nc] == 0) {
                    newList.add(new Point(i,nr,nc));
                }
            }

            // 상하 확인
            for (int ii = 0; ii < 2; ii++) {
                int nh = i + dh[ii];

                // 범위 벗어난 경우 스킵
                if (nh < 0 || nh >= h) continue;

                if (tomatoes[nh][j][k] == 0) {
                    newList.add(new Point(nh,j,k));
                }
            }
        }

        // 적용
        for (Point point : newList) {
            tomatoes[point.x][point.y][point.z] = 1;
        }
        
        // 기존 체크리스트 교체
        checkList = newList;
    }
}

class Point {

    int x;
    int y;
    int z;

    Point (int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        if (this.x == point.x && this.y == point.y && this.z == point.z) return true;
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x,y,z);
    }
}
