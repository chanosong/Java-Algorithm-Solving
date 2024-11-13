package Baekjoon.bj1406;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1406
// 에디터


public class bj1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        StringBuffer sb = new StringBuffer(input);

        int cnt = Integer.parseInt(br.readLine());

        int cursor = input.length();

        for (int i = 0; i < cnt; i++) {

            String[] cmd = br.readLine().split(" ");

            switch (cmd[0]) {
                case "L":
                    cursor = Math.max(0, cursor - 1);
                    break;
                case "D":
                    cursor = Math.min(sb.length(), cursor + 1);
                    break;
                case "B":
                    if(cursor > 0) sb.deleteCharAt(cursor - 1);
                    cursor = Math.max(Math.min(sb.length(), cursor - 1), 0);
                    break;
                case "P":
                    sb.insert(cursor, cmd[1]);
                    cursor++;
            }
        }

        System.out.println(sb);
    }
}
