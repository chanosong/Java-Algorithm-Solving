package Baekjoon.bj2877;

// https://www.acmicpc.net/problem/2877
// 4와 7

import java.io.*;

public class bj2877 {

    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());

        k += 1;

        StringBuilder sb = new StringBuilder();

        while (k > 0) {
            sb.append(k % 2);
            k /= 2;
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.reverse();

        StringBuilder newSb = new StringBuilder();

        for (String s : sb.toString().split("")) {
            if (s.equals("0")) newSb.append("4");
            else newSb.append("7");
        }

        System.out.println(newSb);
    }
}
