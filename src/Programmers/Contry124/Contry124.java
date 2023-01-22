package Programmers.Contry124;
// https://school.programmers.co.kr/learn/courses/30/lessons/12899
public class Contry124 {
    public static void main(String[] args) {
        int n = 13;
        String answer = "";
        int[] rule = {1,2,4};
        int r;

        while (true) {
            n = n / 3;
            r = n % 3;

            answer = rule[r] + answer;
            if (n == 0) break;
        }

        System.out.println(answer);
    }
}
