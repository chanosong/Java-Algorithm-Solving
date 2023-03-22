package Programmers.LackingAmount;
// https://school.programmers.co.kr/learn/courses/30/lessons/82612
public class LackingAmount {
    public static void main(String[] args) {
        int price = 3;
        int money = 20;
        int count = 4;
        long answer = money;

        for (int i = 1; i <= count; i++) {
            answer -= price * i;
        }

        if (answer >= 0) answer = 0;

        System.out.println(answer * - 1);
    }
}
