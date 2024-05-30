package Baekjoon.bj1522;

// https://www.acmicpc.net/problem/1522
// 1522 문자열 교환

import java.io.*;

public class bj1522 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split("");

        int cnt = 0;

        for (String s: strings) {
            if (s.equals("a")) cnt++;
        }

        int start = 0;
        int end = cnt - 1;

        int answer = 0;

        for (int i = start; i <= end; i++) {
            if (strings[i].equals("b")) {
                answer++;
            }
        }

        int cntB = answer;

        while (start < strings.length - 1) {

            if (strings[start].equals("b"))  cntB--;
            if (strings[(end + 1) % strings.length].equals("b"))  cntB++;

            start++;
            end++;

            answer = Math.min(answer, cntB);
        }

        System.out.println(answer);

    }
}
