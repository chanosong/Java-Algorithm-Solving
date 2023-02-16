package Programmers.HashTableFunc;
// https://school.programmers.co.kr/learn/courses/30/lessons/147354

import java.util.*;

public class HashTableFunc {
    public static void main(String[] args) {
        int[][] data = {{2,2,6},{1,5,10},{4,2,9},{3,8,3}};
        int col = 2;
        int row_begin = 2;
        int row_end = 3;
        int answer = 0;

        List<Integer[]> list = new ArrayList<>();
        List<Integer> res = new ArrayList<>();

        for (int[] now : data) {

            Integer[] convertedNow = Arrays.stream(now).boxed().toArray(Integer[]::new);
            // 빈 경우 일단 삽입
            if (list.isEmpty()) {
                list.add(convertedNow);
                continue;
            }

            for (int i = 0; i < list.size(); i++) {
                // col 번째 기준으로 비교
                // 같은 경우 맨 앞을 기준으로 비교
                Integer[] cur = list.get(i);

                // 기준 값이 같은 경우
                if (cur[col - 1] == now[col - 1]) {
                    // 대표 값이 더 큰 경우 앞에 삽입
                    if (cur[0] <= now[0]) {
                        list.add(i, convertedNow);
                        break;
                    }
                    // 그렇지 않은 경우 일단 넘김
                }
                // 기준 값이 더 작은 경우
                else if (cur[col - 1] > now[col - 1]) {
                    list.add(i, convertedNow);
                    break;
                }

                // 그렇지 않은 경우에 끝까지 온 경우 마지막에 삽입
                if (i == list.size() - 1) {
                    list.add(convertedNow);
                    break;
                }
            }
        }
        //System.out.println(list.toString());

        for (int i = row_begin; i <= row_end; i++) {
            int tmp = 0;
            Integer[] now = list.get(i - 1);

            for (int j : now) {
                tmp += j % i;
            }

            res.add(tmp);
        }

        answer = res.get(0);
        for (int i = 1; i < res.size(); i++) {
            answer = answer ^ res.get(i);
        }

        System.out.println(answer);
    }
}
