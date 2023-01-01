package Programmers.SocialDistance;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/81302
 */
public class SocialDistance {

    static class Procedure {
        int depth;
        int x;
        int y;

        Procedure(int depth, int x, int y) {
            this.depth = depth;
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}
                , {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOXP"}
                , {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}
                , {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}
                , {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        String[][] places1 = {{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOXP"}};

        int[] answer = new int[places.length];
        int[] dx = {0,-1,0,1};
        int[] dy = {-1,0,1,0};
        int limit = 5;

        for (int i = 0; i < places.length; i++) {
            int isBreak = 0;

            for (int j = 0; j < limit; j++) {
                // no doubt
                if (isBreak == 1) break;

                for (int k = 0; k < limit; k++) {
                    // no doubt
                    if (isBreak == 1) break;

                    // if is not person
                    if (places[i][j].charAt(k) != 'P') continue;

                    // if is person, BFS
                    Queue<Procedure> queue = new LinkedList<>();
                    int[][] visited = new int[limit][limit];

                    queue.add(new Procedure(0, j, k));
                    visited[j][k] = 1;

                    while (!queue.isEmpty() && isBreak == 0) {
                        Procedure now = queue.poll();

                        // check Manhattan distance
                        if (now.depth >= 2) continue;

                        // if distance <= 2 and Person nearby, break
                        if (now.depth != 0 && places[i][now.x].charAt(now.y) == 'P') {
                            isBreak = 1;
                            break;
                        }

                        // check person
                        for (int m = 0; m < 4; m++) {
                            int nx = now.x + dx[m];
                            int ny = now.y + dy[m];

                            // if out of range, skip
                            if (nx < 0 || nx >= limit || ny < 0 || ny >= limit) continue;
                            // if visited, skip
                            if (visited[nx][ny] == 1) continue;
                            // if person right after it
                            if (places[i][nx].charAt(ny) == 'P') {
                                isBreak = 1;
                                break;
                            }
                            // if partitioned, skip
                            if (places[i][nx].charAt(ny) == 'X') continue;

                            queue.add(new Procedure(now.depth + 1, nx, ny));
                            visited[nx][ny] = 1;
                        }

                    }
                }
            }

            // check distance
            if (isBreak != 1) answer[i] = 1;
        }
        System.out.println(Arrays.toString(answer));
    }
}
