package Programmers.BehindLargeNum;
// https://school.programmers.co.kr/learn/courses/30/lessons/154539

import java.util.Arrays;

public class BehindLargeNum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{2,3,3,5})));
        System.out.println(Arrays.toString(solution(new int[]{9,1,5,3,6,2})));
    }

    static int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];

        // 초기 세팅
        answer[numbers.length - 1] = -1;
        int bigger = numbers[numbers.length - 1];
        
        // 맨 앞까지 반복
        for (int i = numbers.length - 2; i >= 0; i--) {

            if (numbers[i] < numbers[i + 1]) {
                bigger = numbers[i + 1];
                answer[i] = bigger;
            }
            else if (numbers[i] == numbers[i + 1]){
                answer[i] = answer[i + 1];
            }
            else {
                answer[i] = -1;
            }
        }

        return answer;
    }
}
