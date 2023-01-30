package Programmers.Contry124;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/12899
public class Contry124 {
    public static void main(String[] args) {
        int n = 10;
        String answer = "";
        int[] rule = {1,2,4};
        int r;
        StringBuffer sb = new StringBuffer();

        while (true) {
            n -= 1;
            r = n % 3;
            n = n / 3;

            sb.append(rule[r]);
            if (n == 0) break;
        }
        answer = sb.reverse().toString();
        System.out.println(answer);
    }
}
