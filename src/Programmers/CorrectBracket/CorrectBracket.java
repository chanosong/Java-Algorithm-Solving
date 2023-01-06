package Programmers.CorrectBracket;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/12909
 */
import java.util.*;

public class CorrectBracket {
    public static void main(String[] args) {
        String s = "(()(";
        boolean answer = true;
        int cnt = 0;
        char[] stoken = s.toCharArray();

        for (char token : stoken) {

            if (token == ')') {
                if (cnt == 0) {
                    answer = false;
                    break;
                }
                if (cnt > 0) {
                    cnt--;
                    continue;
                }
                answer = false;
                break;
            }
            cnt++;
        }

        if (cnt > 0) answer = false;

        System.out.println(answer);
    }
}
