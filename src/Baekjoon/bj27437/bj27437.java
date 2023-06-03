package Baekjoon.bj27437;

// https://www.acmicpc.net/problem/27437
// 분수 찾기 2

import java.util.Scanner;

public class bj27437 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long x = sc.nextLong();

        long count = 0L;
        long adder = 1L;

        while (count < x) {
            count += adder++;
        }
        count -= adder - 1;

        long a = x - count;
        long b = adder - (x - count);

        if (adder % 2 == 0) {
            System.out.println(b + "/" + a);
        }
        else {
            System.out.println(a + "/" + b);
        }
    }
}
