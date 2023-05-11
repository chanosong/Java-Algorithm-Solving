package Baekjoon.bj17276;
// 17276 배열 돌리기
// https://www.acmicpc.net/problem/17276
import java.util.Arrays;
import java.util.Scanner;

public class bj17276 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int d = sc.nextInt();

            int[][] map = new int[n][n];

            sc.nextLine();

            // 맵 저장
            for (int j = 0; j < n; j++) {
                map[j] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int[][] temp = new int[n][n];
            
            // 복사
            copyZeroZone(map, temp);

            int time;
            // 양의 각도일 경우
            if (d > 0) {
                time = (d % 360) / 45;
            }
            // 음의 각도일 경우
            else {
                time = (360 - Math.abs((d % 360))) / 45;
            }

            for (int j = 0; j < time; j++) {
                map = rotate(map, n);
            }

            copyZeroZone(temp, map);

            printMap(map);
        }
    }
    static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[][] rotate(int[][] map, int n) {
        int[][] copy = new int[n][n];

        // 주 대각선 -> (n+1) / 2 열
        for (int i = 0; i < n; i++) {
            copy[i][n/2] = map[i][i];
        }

        // 가운데열 -> 부 대각선 ((n,1),(n-1,2), ... ,(1,n))
        for (int i = 0; i < n; i++) {
            copy[i][n - 1 - i] = map[i][n/2];
        }
        
        // 부 대각선 -> (n+1) / 2 행
        for (int i = 0; i < n; i++) {
            copy[n/2][i] = map[n - 1 - i][i];
        }

        // 가운데 행 -> 주 대각선
        for (int i = 0; i < n; i++) {
            copy[i][i] = map[n/2][i];
        }

        /*
        // 나머지 복사
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] == 0) {
                    copy[i][j] = map[i][j];
                }
            }
        }
         */

        return copy;
    }

    // res 칸의 값이 0인 곳만 map -> res로 복사
    static void copyZeroZone(int[][] map, int[][] res) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (res[i][j] == 0) res[i][j] = map[i][j];
            }
        }
    }
}
