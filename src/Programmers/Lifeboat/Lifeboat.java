package Programmers.Lifeboat;
// https://school.programmers.co.kr/learn/courses/30/lessons/42885

import java.util.*;

public class Lifeboat {
    public static void main(String[] args) {
        int[] people = {70,50,80,50};
        int limit = 100;
        
        Integer[] converted = Arrays.stream(people).boxed().toArray(Integer[]::new);
        int answer = 0;
        Stack<Integer> rest = new Stack<>();
        Arrays.sort(converted, Collections.reverseOrder());
        
        for (int w : converted) {
            // 완전히 동일한 경우 볼 필요 없이 증가
            if (w == limit) {
                answer++;
                continue;
            }
            
            // limit의 절반보다 무거운 경우들 끼리는 서로 타지 못하니 분배
            if (w > limit / 2) {
                rest.push(w);
                continue;
            }

            // 내림차순으로 정렬했을때 가장 널널한 곳에 들어가지 않으면 OUT
            if (rest.peek() + w <= limit) {
                rest.pop();
                answer++;
                continue;
            }

            rest.push(w);
        }

        answer += rest.size();

        System.out.println(answer);
    }
}
