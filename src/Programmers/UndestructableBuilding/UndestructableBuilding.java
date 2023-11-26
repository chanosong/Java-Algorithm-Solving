package Programmers.UndestructableBuilding;

// https://school.programmers.co.kr/learn/courses/30/lessons/92344 파괴되지 않은 건물

public class UndestructableBuilding {

    static int [][] board = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
    static int [][] skill = new int[][] {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};
    static int n;
    static int m;
    static int[][] map;
    public static void main(String[] args) {
        int answer = 0;

        n = board.length;
        m = board[0].length;

        // 변화량 저장용
        map = new int[n + 1][m + 1];

        for (int i = 0; i < skill.length; i++) {

            int[] now = skill[i];

            int change;

            if (skill[i][0] == 1) {
                change = -1 * now[5];
            }
            else {
                change = now[5];
            }

            // 시작지점 상쇄
            map[now[1]][now[2]] += change;

            // 같은 줄에서 상쇄
            map[now[1]][now[4] + 1] -= change;

            // 끝줄 시작 지점 상쇄
            map[now[3] + 1][now[2]] -= change;

            // 끝줄 마지막 상쇄
            map[now[3] + 1][now[4] + 1] += change;
        }

        // board에 적용
        // 세로 적용
        for (int j = 0; j < m + 1; j++) {
            int sum = map[0][j];
            for (int i = 1; i < n + 1; i++) {
                sum += map[i][j];
                map[i][j] = sum;
            }
        }

        // 가로 적용
        for (int i = 0; i < n + 1; i++) {
            int sum = map[i][0];
            for (int j = 1; j < m + 1; j++) {
                sum += map[i][j];
                map[i][j] = sum;
            }
        }

        // 더하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + map[i][j] > 0) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    static void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printMap() {
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }
}
