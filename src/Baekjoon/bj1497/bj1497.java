package Baekjoon.bj1497;
// https://www.acmicpc.net/problem/1497
// 1497 기타콘서트

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1497 {

    static int n;
    static int m;
    static int maxSong = 0;
    static int answer = -1;
    static Guitar[] guitars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        guitars = new Guitar[n];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");

            guitars[i] = new Guitar(line[0], line[1]);
        }

        // 1 ~ N개 조합
        for (int i = 1; i <= n; i++) {
            comb(new boolean[n], 0, 0, i);
        }

        System.out.println(answer);
    }

    static void comb(boolean[] visited, int start, int depth, int r) {
        if (r == depth) {

            /*
            for (int i = 0; i < n; i++) {
                System.out.print(visited[i] + " ");
            }
            System.out.println();

             */

            boolean[] playedSong = new boolean[m];
            int playedCnt = 0;

            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    for (int j = 0; j < m; j++) {
                        if (!playedSong[j] && guitars[i].playlist[j].equals("Y")) {
                            playedCnt++;
                            playedSong[j] = true;
                        }
                    }
                }
            }

            if (maxSong < playedCnt) {
                maxSong = playedCnt;
                answer = r;
            }
            return;
        }

        for (int i = start; i < visited.length; i++) {
            visited[i] = true;
            comb(visited, i + 1,  depth + 1, r);
            visited[i] = false;
        }
    }
}

class Guitar {
    String name;
    String[] playlist;

    Guitar(String name, String num) {
        this.name = name;
        playlist = num.split("");
    }
}
