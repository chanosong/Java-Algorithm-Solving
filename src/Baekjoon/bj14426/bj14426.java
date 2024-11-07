package Baekjoon.bj14426;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14426
// 접두사 찾기

public class bj14426 {

    static List<Token> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        List<Token> rootList = list;

        for (int i = 0; i < n; i++) {
            list = rootList;

            String[] str = br.readLine().split("");

            for (int j = 0; j < str.length; j++) {

                int idx = getIndex(list, str[j]);

                Token nextToken;

                if (idx == -1) {
                    Token newToken = new Token(str[j]);

                    list.add(newToken);

                    nextToken = newToken;
                }
                else {
                    nextToken = list.get(idx);
                }

                list = nextToken.nextStr;
            }
        }

        int answer = 0;

        for (int i = 0; i < m; i++) {
            String[] str = br.readLine().split("");

            List<Token> cursor = rootList;

            boolean isGood = true;

            for (int j = 0; j < str.length; j++) {
                int idx = getIndex(cursor, str[j]);

                if (idx == -1) {
                    isGood = false;
                    break;
                }

                cursor = cursor.get(idx).nextStr;
            }

            if (isGood) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static public int getIndex (List<Token> list, String str) {
        int ret = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).str.equals(str)) {
                ret = i;
                break;
            }
        }

        return ret;
    }
}

class Token {
    String str;
    List<Token> nextStr = new ArrayList<>();

    Token(String str) {
        this.str = str;
    }
}