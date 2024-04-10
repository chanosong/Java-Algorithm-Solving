package Baekjoon.bj1141;

// https://www.acmicpc.net/problem/1141
// 1141 접두사

import java.io.*;
import java.util.*;

public class bj1141 {
    static int n;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        List<List<String>> wordList = new ArrayList<>();

        // 알파펫 첫 글자에 따른 구분
        for (int i = 0; i < 26; i++) {
            wordList.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            String word = br.readLine();

            wordList.get(word.charAt(0) - 'a').add(word);
        }

        // 정렬 및 가짓수 합산
        for (List<String> list : wordList) {
            // 해당 알파벳으로 시작되는 단어가 없는 경우
            if (list.isEmpty()) continue;

            Collections.sort(list, Collections.reverseOrder());

            int[] cnt = new int[list.size()];

            int tmp = 0;

            for (int start = 0; start < list.size(); start++) {
                String now = list.get(start);

                for (int j = start; j < cnt.length; j++ ) {
                    // 이미 같은 군이 있는 경우
                    if (cnt[j] != 0) continue;
                    // 처음인 경우
                    if (j == start) {
                        cnt[j] = ++tmp;
                        continue;
                    }
                    // 비교군과 똑같이 시작하는 경우
                    if (now.startsWith(list.get(j))) {
                        cnt[j] = tmp;
                    }
                }
            }
            answer += tmp;
        }

        System.out.println(answer);
    }

    static boolean isSub(String s1, String s2) {
        String[] arr1 = s1.split("");
        String[] arr2 = s2.split("");

        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) return false;
        }

        return true;
    }
}