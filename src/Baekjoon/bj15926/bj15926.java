package Baekjoon.bj15926;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/15926
// 15926 현욱은 괄호왕이야!!

public class bj15926 {
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        String[] str = br.readLine().split("");

        int answer = 0;

        Stack<Token> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            Token tk = new Token(i, str[i]);

            if (stack.isEmpty() || str[i].equals("(")) stack.push(tk);
            else {
                if(stack.peek().str.equals("(")) {
                    stack.pop();
                    continue;
                }
                stack.push(tk);
            }
        }

        if (stack.isEmpty()) answer = str.length;
        else {

            Token tk = stack.pop();

            int lastIdx = tk.idx;
            answer = Math.max(str.length - (lastIdx + 1), answer);

            while (!stack.isEmpty()) {
                Token tmp = stack.pop();

                answer = Math.max(answer, lastIdx - (tmp.idx + 1));
                lastIdx = tmp.idx;
            }

            answer = Math.max(answer, lastIdx);
        }

        System.out.println(answer);
    }
}

class Token {
    int idx;
    String str;

    public Token(int idx, String str) {
        this.idx = idx;
        this.str = str;
    }
}
