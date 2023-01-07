package Programmers.LargestSquare;
//https://school.programmers.co.kr/learn/courses/30/lessons/12905
public class LargestSquare {
    public static void main(String[] args) {

        int[][] board = {{0,1,1,1}
                        ,{1,1,1,1}
                        ,{1,0,1,1}
                        ,{0,0,1,0}};

        //int[][] board = {{1}};
        int answer = 0;
        int row = board.length;
        int col = board[0].length;
        /*
        boolean[][] check = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // if 0, skip
                if (board[i][j] == 0 && check[i][j] == false) continue;
                int hcnt = 1;
                int vcnt = 1;
                boolean isSquare = true;

                // check horizontal length
                for (int k = j + 1; k < col; k++) {
                    if (board[i][k] == 0) break;
                    hcnt++;
                }
                // check vertical length
                for (int k = i + 1; k < row; k++) {
                    if (board[k][j] == 0) break;
                    vcnt++;
                }
                int lim = Math.min(hcnt, vcnt);
                // check left things
                for (int m = 1; m < lim; m++) {
                    for (int n = 1; n < lim; n++) {
                        if (board[i + m][j + n] == 0) {
                            isSquare = false;
                            break;
                        }
                    }
                    if (!isSquare) break;
                }
                if (isSquare) {
                    for (int m = 0; m < lim; m++){
                        for (int n = 0; n < lim; n++) {
                            check[m][n] = true;
                        }
                    }

                    answer = Math.max(answer, (int)Math.pow(lim,2));
                }
            }
        }
         */

        int[][] map = new int[row + 1][col + 1];
        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < col + 1; j++) {
                if (board[i - 1][j - 1] == 1) {
                    map[i][j] = Math.min(Math.min(map[i-1][j], map[i][j-1]), map[i-1][j-1]) + 1;
                    answer = Math.max(map[i][j], answer);
                }
            }
        }
        answer = (int)Math.pow(answer, 2);

        System.out.println(answer);
    }
}
