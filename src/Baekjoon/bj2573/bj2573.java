package Baekjoon.bj2573;
// https://www.acmicpc.net/problem/2573

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj2573 {

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // n,m 입력
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 버퍼클리어
        sc.nextLine();

        // 지도 입력
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.asList(sc.nextLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();
        }

        // 소요 시간 카운터
        int time = 0;

        // 덩어리 확인
        while(check(map)) {
            int total = passTime(map);
            // 2 덩이로 나누어지기 전에 다 녹는 경우 0으로 세팅 후 아웃
            if (total == 0) {
                time = 0;
                break;
            }
            time++;
        }


        System.out.println(time);
    }

    static int passTime(int[][] map) {
        int[][] newMap = new int[map.length][map[0].length];
        // 빙산의 개수 리턴
        int total = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                // 이미 다 녹은 곳인 경우
                if (map[i][j] == 0) continue;

                // 녹은 곳이 아닌 경우 사방 확인 후 녹아내리기
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    // 지도 밖인 경우 스킵
                    if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
                    // 이미 녹은 지역인 경우 카운터 증가
                    if (map[nx][ny] == 0) cnt++;
                }

                // 녹은 정도 적용
                newMap[i][j] = map[i][j] -  cnt;
                // 0보다 작을 시 0으로 맞춰줌
                if (newMap[i][j] < 0) newMap[i][j] = 0;
            }
        }

        // newMap을 map에 적용
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = newMap[i][j];
                if (map[i][j] > 0) total++;
            }
        }

        return total;
    }

    static boolean check(int[][] map) {

        // 총 빙산 개수와 한 덩어리의 빙산 개수
        int total = 0;
        int cnt = 0;

        // 빙산 위치 저장 변수
        int checkX = 0;
        int checkY = 0;

        // 빙산의 총 개수 계산
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0){
                    total++;
                    checkX = i;
                    checkY = j;
                }
            }
        }

        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<Integer[]> queue = new LinkedList<>();

        // 시작점 세팅
        queue.add(new Integer[]{checkX, checkY});
        visited[checkX][checkY] = true;

        // BFS
        while (!queue.isEmpty()) {
            Integer[] now = queue.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                // 맵을 벗어나는 경우거나 빙산이 없는 곳, 이미 방문한 곳이라면 스킵
                if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length || map[nx][ny] == 0 || visited[nx][ny]) continue;

                // 이어진 빙산인 경우 진행 후 개수 증가
                queue.add(new Integer[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        return total == cnt;
    }
}
