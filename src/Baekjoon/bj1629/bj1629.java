package Baekjoon.bj1629;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1629
// 곱셈

public class bj1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        long answer = pow(a, b, c);

        System.out.println(answer);
    }

    static long pow (long a, long b, long c) {

        if (b == 1) return a % c;

        long tmp = pow(a, b / 2, c);

        if (b % 2 == 1) {
            return (tmp * tmp % c) * a % c;
        }
        return tmp * tmp % c;
    }
}
