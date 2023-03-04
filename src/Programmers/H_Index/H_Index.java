package Programmers.H_Index;

import java.util.Arrays;
// https://school.programmers.co.kr/learn/courses/30/lessons/42747
public class H_Index {
    public static void main(String[] args) {
        int[] citations = {1,1,1,1};
        int answer = 0;

        Arrays.sort(citations);

        for (int i = citations.length - 1; i >= 0; i--) {

            // 현재 숫자가 카운터보다 높아지는 경우에는 종료
            if (answer >= citations[i]) break;
            answer++;
        }

        System.out.println(answer);
    }
}
