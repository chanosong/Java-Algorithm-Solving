package Baekjoon.bj1748;

// https://www.acmicpc.net/problem/1748
// 1748 수 이어 쓰기 1

import java.io.*;
import java.util.*;

public class bj1748 {

    static String n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = br.readLine();

        int len = n.length();

        long answer = 0;
        long add = 9;

        for (int i = 0; i < len; i++) {
            if (i == len - 1) answer += (long) ((Long.parseLong(n) - Math.pow(10, i) + 1) * (i + 1));
            else answer += (add * (i + 1));
            add *= 10;
        }

        System.out.println(answer);
    }
}
