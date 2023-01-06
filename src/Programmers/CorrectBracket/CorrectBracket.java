package Programmers.CorrectBracket;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/12909
 */
import java.util.*;

public class CorrectBracket {
    public static void main(String[] args) {
        String s = "(()(";
        boolean answer = true;
        Stack<Character> st = new Stack<>();
        char[] stoken = s.toCharArray();

        for (char token : stoken) {

            if (token == ')') {
                if (st.isEmpty()) {
                    answer = false;
                    break;
                }
                if (st.peek() == '(') {
                    st.pop();
                    continue;
                }
                answer = false;
                break;
            }
            st.add(token);
        }

        if (!st.isEmpty()) answer = false;

        System.out.println(answer);
    }
}
