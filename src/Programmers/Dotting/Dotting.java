package Programmers.Dotting;
// https://school.programmers.co.kr/learn/courses/30/lessons/140107
public class Dotting {
    public static void main(String[] args) {
        int k = 2;
        int d = 4;
        long answer = 0;

        for (long i = 0; i <= d; i = i + k) {
            long tmp = ((long)Math.pow(d,2) - (long) Math.pow(i,2));
            answer += Math.sqrt(tmp) / k + 1;
        }

        System.out.println(answer);
    }

}

