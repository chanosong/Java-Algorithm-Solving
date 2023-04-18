package Baekjoon.bj2667;
// https://www.acmicpc.net/problem/2667
import java.util.*;

public class bj2667 {

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};

    static int[][] map;

    static boolean[][] visited;

    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        List<Integer> list = new LinkedList<>();

        // 버퍼 초기화
        sc.nextLine();

        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 집이 아니거나 이미 방문한 곳이면 스킵
                if (map[i][j] == 0 || visited[i][j]) continue;
                
                // 새로운 단지인 경우 visit 처리
                list.add(check(i,j));
            }
        }

        Collections.sort(list);

        System.out.println(list.size());
        for (int c : list) {
            System.out.println(c);
        }
    }

    static int check(int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        visited[r][c] = true;
        queue.add(new Point(r,c));

        int houseCnt = 1;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                // 벗어난 경우, 이미 방문한 경우, 집이 아닌 경우 스킵
                if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc] || map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.add(new Point(nr,nc));
                houseCnt++;
            }
        }

        return houseCnt;
    }
}

class Point {
    int r;
    int c;
    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
