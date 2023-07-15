package Baekjoon.bj2668;

// https://www.acmicpc.net/problem/2668
// 2668 숫자 고르기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class bj2668 {

    static int n;
    static int[] arr;
    static boolean[] visited;
    static Integer[] answerSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        checkSelfTarget();

        for (int i = 1; i < n + 1; i++) {
            if (visited[i]) continue;
            checkRotate(i);
        }

        int cnt = 0;

        for (int i = 1; i < n + 1; i++) {
            if (visited[i]) cnt++;
        }

        answerSet = new Integer[cnt];

        int cursor = 0;

        for (int i = 1; i < n + 1; i++) {
            if (!visited[i]) continue;
            answerSet[cursor++] = arr[i];
        }

        Arrays.sort(answerSet);

        System.out.println(answerSet.length);

        for (int answer : answerSet) {
            System.out.println(answer);
        }
    }

    static void checkSelfTarget() {
        for (int i = 1; i < n + 1; i++) {
            if (i != arr[i]) continue;
            visited[i] = true;
        }
    }

    // 해당 idx에서 시작해서 몇 단계까지 갈 수 있는지 확인
    static void checkRotate(int idx) {
        Procedure trial = new Procedure(idx);

        boolean[] _visited = visited.clone();
        _visited[idx] = true;

        while (true) {
            int target = arr[trial.getLast()];

            // 한 바퀴 돌아온 경우 종료
            if (target == trial.getFirst()) {
                visited = _visited;
                break;
            }

            // 타겟이 이미 간 곳인 경우 조건 만족하지 않으며 종료
            if (_visited[target]) break;

            // 그렇지 않은 경우 계속 진행
            trial.add(target);
            _visited[target] = true;
        }
    }
}

class Procedure {

    List<Integer> trace;

    Procedure(int start) {
        trace = new LinkedList<>();
        trace.add(start);
    }

    public void add(int idx) {
        trace.add(idx);
    }

    public int getFirst() {
        return trace.get(0);
    }
    public int getLast() {
        return trace.get(trace.size() - 1);
    }

    public int getSize() {
        return trace.size();
    }
}