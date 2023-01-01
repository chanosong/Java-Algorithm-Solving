package Programmers.StockPrice;
/*
https://school.programmers.co.kr/learn/courses/30/lessons/42584
 */
import java.util.Arrays;

public class StockPrice {
    public static void main(String[] args) {
        int[] prices = {1,2,3,2,3};
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            int cnt = 0;
            for (int j = i + 1; j < prices.length; j++) {
                cnt++;
                if (prices[i] > prices[j]) break;
            }
            answer[i] = cnt;
        }

        System.out.println(Arrays.toString(answer));
    }
}
