package Baekjoon.bj14503;
// https://www.acmicpc.net/problem/14503
import java.util.Arrays;
import java.util.Scanner;

public class bj14503 {
    // 북 동 남 서 순서
    static int[] dy = {0,1,0,-1};
    static int[] dx = {-1,0,1,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 방의 크기 입력
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 로봇의 위치 입력
        int r = sc.nextInt();
        int c = sc.nextInt();

        // 방향 입력
        int d = sc.nextInt();
        // 버퍼 초기화
        sc.nextLine();

        // map 정보 입력
        int[][] map = new int[n][m];

        // map 업데이트
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = clean(r,c,d,0,map);

        System.out.println(answer);
    }

    static int clean(int r, int c, int d, int cnt, int[][] map) {

        // 현재 칸이 청소가 필요한 경우 
        // 청소 횟수 증가
        if (map[r][c] == 0) {
            cnt++;
            map[r][c] = 2;
        }

        for (int i = 0; i < 4; i++) {

            // 자기 기준으로 반시계 90도 씩 회전
            int nd = (d + 3 - i) % 4;
            // 현재 바라보는 칸
            int nr = r + dx[nd];
            int nc = c + dy[nd];

            // 벽이 거나 이미 청소한 곳이면 넘어감
            if (map[nr][nc] >= 1) continue;

            // 바라보는 방향이 청소할 곳이라면 이동
            return clean(nr, nc, nd, cnt, map);
        }

        // 사방에 청소할 곳이 없는 경우 후진 고려
        int nr = r - dx[d];
        int nc = c - dy[d];

        // 만약 뒤가 벽이라면 종료
        if (map[nr][nc] == 1) return cnt;

        // 후진이 가능하다면 방향 유지하고 뒷 무빙
        return clean(nr, nc, d, cnt, map);
    }

}
