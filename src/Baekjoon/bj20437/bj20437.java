package Baekjoon.bj20437;

// https://www.acmicpc.net/problem/20437
// 20437 문자열 게임 2

import java.io.*;
import java.util.*;

public class bj20437 {
    static int t;
    static int k;
    static String w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            w = br.readLine();
            k = Integer.parseInt(br.readLine());

            List<List<Integer>> idxList = new ArrayList<>();
            
            for (int j = 0; j < 28; j++) {
                idxList.add(new ArrayList<>());
            }
            
            // 각 알파벳의 인덱스 추가
            for (int j = 0; j < w.length(); j++) {
                idxList.get(w.charAt(j) - 'a').add(j);
            }

            // 확인할 후보군
            List<Integer> alpha = new ArrayList<>();

            for (int j = 0; j < 28; j++) {
                if (idxList.get(j).size() >= k) alpha.add(j);
            }

            // 만약 k개 이상 나오는 알파벳이 아예 없는 경우
            if (alpha.size() == 0) {
                System.out.println(-1);
            }
            else {
                // k 개 이상 나오는 알파벳이 나오는 경우
                int minLength = Integer.MAX_VALUE;
                int maxLength = Integer.MIN_VALUE;

                for (int idx : alpha) {
                    List<Integer> tmpList = idxList.get(idx);

                    for (int j = 0; j < tmpList.size() - k + 1; j++) {
                        int tmpLength = tmpList.get(k + j - 1) - tmpList.get(j) + 1;
                        
                        // 더 작을 경우 갱신
                        if (tmpLength < minLength) {
                            minLength = tmpLength;
                        }
                        // 큰 경우 갱신
                        if (tmpLength > maxLength) {
                            maxLength = tmpLength;
                        }
                    }
                }

                // 출력
                System.out.println(minLength + " " + maxLength);
            }
        }
    }
}
