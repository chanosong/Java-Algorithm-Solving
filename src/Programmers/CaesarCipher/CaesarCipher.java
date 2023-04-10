package Programmers.CaesarCipher;

// https://school.programmers.co.kr/learn/courses/30/lessons/12926

public class CaesarCipher {
    public static void main(String[] args) {
        System.out.println(solution("AB", 1));
        System.out.println(solution("z", 1));
        System.out.println(solution("a B z", 4));
    }

    static String solution(String s, int n) {
        StringBuffer sb = new StringBuffer();

        char[] arr = s.toCharArray();

        for (char c : arr) {
            // 빈칸인 경우 그냥 추가
            if (c == ' ') {
                sb.append(c);
                continue;
            }

            char input;
            // 대문자인 경우
            if (Character.isUpperCase(c)) {
                input = (char) ((c - 65 + n) % 26 + 65);
            } else {
                // 소문자인 경우
                input = (char) ((c - 97 + n) % 26 + 97);
            }

            sb.append(input);
        }

        String answer = sb.toString();
        return answer;
    }
}
