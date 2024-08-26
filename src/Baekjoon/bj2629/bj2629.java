package Baekjoon.bj2629;

import java.io.*;
import java.util.*;

public class bj2629 {

    static int n;
    static int[] biz;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        biz = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        m = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가능한 숫자 확인
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            int cnt = 0;
            boolean isPossible = false;

            for (int j = n - 1; j >= 0; j--) {
                if (target >= cnt) {
                    cnt += biz[j];
                }
                else if (target == cnt) {
                    isPossible = true;
                    break;
                }
                else {
                    if (cnt - biz[j] >= target) {
                        cnt -= biz[j];
                    }
                }
            }

            if (target == cnt) isPossible = true;

            if (isPossible) {
                System.out.print("Y ");
            }
            else {
                System.out.print("N ");
            }
        }
    }
}
