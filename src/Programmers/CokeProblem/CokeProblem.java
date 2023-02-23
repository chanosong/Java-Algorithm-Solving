package Programmers.CokeProblem;
// https://school.programmers.co.kr/learn/courses/30/lessons/132267
public class CokeProblem {
    public static void main(String[] args) {
        int a = 2;
        int b = 1;
        int n = 20;

        int answer = 0;
        while (true) {
            if (n / a == 0) {
                break;
            }

            answer += (n / a) * b;
            n = (n / a) * b + n % a;
        }
        System.out.println(answer);
    }
}
