package Programmers.MockExam;
// https://school.programmers.co.kr/learn/courses/30/lessons/42840
// 모의고사
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MockExam {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1,2,3,4,5})));
        System.out.println(Arrays.toString(solution(new int[]{1,3,2,4,2})));
    }

    static int[] solution(int[] answers) {
        int[] cnt = {0, 0, 0};

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == first(i)) cnt[0]++;
            if (answers[i] == second(i)) cnt[1]++;
            if (answers[i] == third(i)) cnt[2]++;
        }

        List<Integer> list = new LinkedList<>();

        int max = Arrays.stream(cnt).max().getAsInt();

        for (int i = 0; i < 3; i++) {
            if (cnt[i] == max) list.add(i + 1);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // 1번 수포자 -> 1 2 3 4 5
    static int first(int n) {
        int[] pattern = {1,2,3,4,5};

        return pattern[n % 5];
    }

    // 2번 수포자 -> 홀수번은 2 짝수번은 1 3 4 5
    static int second(int n) {
        if (n % 2 == 0) return 2;

        int[] pattern = {1,3,4,5};

        return pattern[(n / 2) % 4];
    }

    // 3번 수포자 -> 3 3 1 1 2 2 4 4 5 5
    static int third(int n) {
        int[] pattern = {3,1,2,4,5};

        return pattern[(n / 2) % 5];
    }
}
