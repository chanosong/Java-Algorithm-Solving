package Baekjoon.bj1092;

// https://www.acmicpc.net/problem/1092
// 배

import java.io.*;
import java.util.*;

public class bj1092 {

    static int n,m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        Integer[] limit = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        m = Integer.parseInt(br.readLine());

        Integer[] weight = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        Arrays.sort(limit, Comparator.reverseOrder());
        Arrays.sort(weight, Comparator.reverseOrder());

        boolean[] packed = new boolean[m];

        int time = 1;
        int totalPackedCnt = 0;
        int packedCnt = 0;

        while (true) {
            boolean[] used = new boolean[n];
            int usedCnt = 0;
            int idx = 0;

            for (int i = 0; i < m; i++) {
                if (packed[i]) continue;

                // 담을수 있는 경우
                if (limit[idx] >= weight[i]) {
                    used[idx] = true;
                    packed[i] = true;
                    totalPackedCnt++;
                    packedCnt++;
                    idx++;
                    usedCnt++;
                }

                if (usedCnt == n) break;
            }

            if (packedCnt == 0) break;
            time++;
            packedCnt = 0;
        }

        if (totalPackedCnt != m) System.out.println(-1);
        else System.out.println(time - 1);
    }
}
