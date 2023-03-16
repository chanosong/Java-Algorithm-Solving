package Programmers.StringBasic;
// https://school.programmers.co.kr/learn/courses/30/lessons/12918
public class StringBasic {
    public static void main(String[] args) {
        String s = "1234";
        boolean answer = true;

        int length = s.length();

        if (length != 4 && length != 6) {
            answer = false;
        }
        else {
            for (int i = 0; i < length; i++) {
                char now = s.charAt(i);
                if (now - '0' < 0 || now - '9' > 0) {
                    answer = false;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}

