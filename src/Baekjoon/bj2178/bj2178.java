package Baekjoon.bj2178;
// https://www.acmicpc.net/problem/2178

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj2178 {

    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int answer = 1;
        sc.nextLine();

        map = new int[n][m];
        visited = new boolean[n][m];

        // map 입력 받기
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        Queue<Integer[]> queue = new LinkedList<>();
        visited[0][0] = true;
        queue.add(new Integer[]{0,0,0});

        while(!queue.isEmpty()) {
            Integer[] now = queue.poll();

            // 도착한 경우 종료
            if (now[0] == n - 1 && now[1] == m - 1) {
                answer = now[2] + 1;
                break;
            }

            // 사방 확인
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                int ns = now[2] + 1;

                // 벗어 나거나 못가는 곳이거나 방문한 곳이면 스킵
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 0 || visited[nx][ny]) continue;

                queue.add(new Integer[]{nx,ny,ns});
                visited[nx][ny] = true;
            }
        }

        System.out.println(answer);
    }
}
