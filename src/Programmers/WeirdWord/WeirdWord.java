package Programmers.WeirdWord;

// https://school.programmers.co.kr/learn/courses/30/lessons/12930

public class WeirdWord {
    public static void main(String[] args) {
        String s = "try hello world";
        String answer = "";

        int cur = 0;

        for (int i = 0; i < s.length(); i++) {
            String now = Character.toString(s.charAt(i));

            if ((cur + 2) % 2 == 0) answer += now.toUpperCase();
            else answer += now.toLowerCase();
            cur++;

            if(now.isBlank()) cur = 0;
        }

        System.out.println(answer);
    }
}
