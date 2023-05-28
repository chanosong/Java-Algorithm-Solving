package Baekjoon.bj1011;

// https://www.acmicpc.net/problem/1011
// Fly me to the Alpha Centauri

import java.util.Scanner;

public class bj1011 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            int length = y - x;

            int maxK = (int) Math.sqrt(length);

            int remain = (int) (length - Math.pow(maxK, 2));

            int cnt = 2 * maxK - 1;

            while (remain > 0) {
                // 더 크다면
                if (remain >= maxK) {
                    remain -= maxK;
                    cnt++;
                    continue;
                }
                maxK--;
            }

            System.out.println(cnt);
        }
    }
}
