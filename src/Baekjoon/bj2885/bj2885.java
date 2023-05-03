package Baekjoon.bj2885;

// https://www.acmicpc.net/problem/2885
// 초콜릿 식사

import java.util.Scanner;

public class bj2885 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k  = sc.nextInt();

        int n = 1;

        while (n < k) {
            n *= 2;
        }

        int part = n;
        int cnt = 0;

        while (k > 0) {
            // 완전히 일치하는 경우
            if (k == part) break;

            // 그렇지 않은 경우 등분 시작
            part /= 2;
            
            // 쟁여둘수 있다면 쟁여두기
            if (k >= part) k -= part;
            cnt++;
        }

        System.out.println(n + " " + cnt);
    }
}
