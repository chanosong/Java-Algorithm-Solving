package Baekjoon.bj2607;

// https://www.acmicpc.net/problem/2607
// 2607 비슷한 단어

import java.util.*;
import java.io.*;

public class bj2607 {

    static int n;

    static int[] cnt = new int[30];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int answer = 0;

        String mainWord = br.readLine();

        for (int j = 0; j < mainWord.length(); j++) {
            cnt[mainWord.charAt(j) - (int) 'A']++;
        }

        for (int i = 0; i < n - 1; i++) {
            String word = br.readLine();

            int[] tmp = new int[30];

            for (int j = 0; j < word.length(); j++) {
                tmp[word.charAt(j) - (int) 'A']++;
            }

            boolean isSame = true;
            int gap = 0;

            // 같은 구성을 갖는지 비교
            for (int j = 0; j < tmp.length; j++) {
                gap += Math.abs(cnt[j] - tmp[j]);
                if (Math.abs(cnt[j] - tmp[j]) >= 2) {
                    isSame = false;
                    break;
                }
            }

            if (isSame && gap <= 2 && gap >= 0 && Math.abs(mainWord.length() - word.length()) < 2) answer++;
        }

        System.out.println(answer);
    }
}
