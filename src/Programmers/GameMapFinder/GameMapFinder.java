package Programmers.GameMapFinder;
// https://school.programmers.co.kr/learn/courses/30/lessons/1844
import java.util.*;
public class GameMapFinder {
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};

    static class Trace {
        int x;
        int y;
        int depth;

        Trace(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        int row = maps.length;
        int col = maps[0].length;
        int answer = -1;

        Queue<Trace> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];

        queue.add(new Trace(0,0,1));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Trace now = queue.poll();

            if (now.x == row - 1 && now.y == col - 1) {
                answer = now.depth;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
                if (maps[nx][ny] == 0) continue;
                if (visited[nx][ny]) continue;

                queue.add(new Trace(nx, ny, now.depth + 1));
                visited[nx][ny] = true;
            }
        }

        System.out.println(answer);
    }
}
