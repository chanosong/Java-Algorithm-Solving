package Baekjoon.bj20920;

// https://www.acmicpc.net/problem/20920
// 20920 영단어 암기는 괴로워

import java.io.*;
import java.util.*;

public class bj20920 {

    static int n,m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();

            if (word.length() < m) continue;
            if(!map.containsKey(word)) {
                map.put(word, 1);
            }
            else map.put(word, map.get(word) + 1);
        }

        Queue<Word> q = new PriorityQueue<>();

        for (String str : map.keySet()) {
            q.add(new Word(str, map.get(str)));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (!q.isEmpty()) {
            bw.write(q.poll().word + "\n");
        }
        bw.flush();
        bw.close();
    }
}

class Word implements Comparable<Word> {

    String word;
    int cnt;

    Word(String word, int cnt) {
        this.word = word;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Word o) {
        // 빈도
        if (this.cnt > o.cnt) return -1;
        else if (this.cnt < o.cnt) return 1;
        // 길이
        if (this.word.length() > o.word.length()) return -1;
        else if (this.word.length() < o.word.length()) return 1;
        // 사전순
        if (this.word.compareTo(o.word) < 0) return -1;
        return 1;
        
    }
}


