package Baekjoon.bj15787;
// https://www.acmicpc.net/problem/15787
// 15787 기차가 어둠을 헤치고 은하수를

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class bj15787 {
    static final int size = 20;
    static int n;
    static int m;
    static int[][] train;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        train = new int[n][size];

        // 버퍼 초기화
        sc.nextLine();

        // M번의 명령 실행
        for (int i = 0; i < m; i++) {
            int[] cmd = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int trainNum = cmd[1] - 1;

            switch (cmd[0]) {
                case 1: // 탑승
                    train[trainNum][cmd[2] - 1] = 1;
                    break;
                case 2: // 하차
                    train[trainNum][cmd[2] - 1] = 0;
                    break;
                case 3: // 한 칸씩 미루기
                    for (int j = 19; j > 0; j--) {
                        train[trainNum][j] = train[trainNum][j - 1];
                    }
                    // 맨 앞자리 비우기
                    train[trainNum][0] = 0;
                    break;
                case 4: // 한 칸씩 앞으로
                    for (int j = 0; j < 19; j++) {
                        train[trainNum][j] = train[trainNum][j + 1];
                    }
                    // 맨 뒷자리 비우기
                    train[trainNum][19] = 0;
            }
        }

        // 명령 실행 후 은하철도 통과하며 String 형태로 기록
        Set<String> record = new HashSet<>();

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();

            for (int p : train[i]) {
                sb.append(p);
            }

            record.add(sb.toString());
        }

        System.out.println(record.size());
    }
}
