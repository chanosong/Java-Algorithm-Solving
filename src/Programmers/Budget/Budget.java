package Programmers.Budget;
// https://school.programmers.co.kr/learn/courses/30/lessons/12982
import java.util.Arrays;

public class Budget {

    public static void main(String[] args) {
        int[] d = {2,2,3,3};
        int budget = 10;
        int answer = 0;

        // 오름차순으로 선 정렬
        Arrays.sort(d);

        for (int cost : d) {
            if (budget >= cost) {
                budget -= cost;
                answer++;
            }
        }

        System.out.println(answer);
    }
}
