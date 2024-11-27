package Baekjoon.bj11507;

// https://www.acmicpc.net/problem/11507
// 11507 카드셋트

import java.io.*;
import java.util.*;

public class bj11507 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        boolean[][] used = new boolean[4][13];

        for (int i = 0; i < str.length() / 3; i++) {
            String token = str.substring(i * 3, i * 3 + 3);

            int idx = -1;

            switch (token.charAt(0)) {
                case 'P':
                    idx = 0;
                    break;
                case 'K':
                    idx = 1;
                    break;
                case 'H':
                    idx = 2;
                    break;
                case 'T':
                    idx = 3;

            }

            int idx2 = Integer.parseInt(token.substring(1,3)) - 1;

            if (used[idx][idx2]) {
                System.out.println("GRESKA");
                return;
            }
            else {
                used[idx][idx2] = true;
            }
        }

        for (int i = 0; i < 4; i++) {
            int cnt = 0;

            for (int j = 0; j < 13; j++) {
                if (!used[i][j]) cnt++;
            }

            System.out.print(cnt + " ");
        }
    }
}
