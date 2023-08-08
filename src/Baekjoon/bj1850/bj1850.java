package Baekjoon.bj1850;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1850 {
     static long a;
     static long b;
     static long answer = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        a = Long.parseLong(input[0]);
        b = Long.parseLong(input[1]);

        long small = Math.min(a,b);
        long big = Math.max(a,b);

        while (small > 1) {
            if (big % small == 0) {
                break;
            }

            long temp = big;
            big = small;
            small = temp % small;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < small; i++) {
            sb.append("1");
        }

        System.out.println(sb);
    }
}
