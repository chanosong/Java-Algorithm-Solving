package Programmers.ArcheryContest;

/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/92342
 */
import java.util.*;

// 총 n발의 화살을 쏴서 이걸 info와 같은 idx에 들어간 경우 패배, 무조건 피해서 고득점으로 노려야한다.

public class ArcheryContest {

    static int max = 0;
    static int[] memo;

    // lion 기준 스코어 계산
    static int calculate(int[] apeach, int[] lion) {
        int scoreGap = 0;

        for (int i = 0; i < 11; i++) {
            // 둘 다 점수가 없는 경우
            if (apeach[i] == 0 && lion[i] == 0) continue;
            if (apeach[i] >= lion[i]) scoreGap -= 10 - i;
            else scoreGap += 10 - i;
        }

        return scoreGap;
    }

    // 작은 점수 순으로 갯수 비교, b가 더 작은 것이 많은 경우 true
    static boolean compare(int[] a, int[] b) {
        for (int i = 10; i >= 0; i--) {
            if (a[i] < b[i]) return true;
            else if (a[i] > b[i]) return false;
        }
        return false;
    }

    static void dfs(int[] apeach, int[] lion, int idx, int cnt) {
        // 스코어 계산
        int score = calculate(apeach, lion);
                
        // 최고점 갱신했을 경우 메모
        if (max < score) {
            max = score;
            memo = lion.clone();
            memo[10] = cnt;
        }
        else if (max == score && max != 0 && compare(memo, lion)) {
            memo = lion.clone();
            memo[10] = cnt;
        }

        // 다 쓴 경우
        if (cnt == 0) return;

        // 더이상 갈 곳이 없는 경우
        if (idx == 10) {
            //lion[10] = cnt;
            return;
        }

        // idx 점수에서 따내는 경우
        // n이 부족한 경우를 생각하여, 이기지못할꺼면 뒤로 보낸다.
        if (cnt > apeach[idx]) {
            lion[idx] = apeach[idx] + 1;
            dfs(apeach, lion, idx + 1, cnt - (apeach[idx] + 1));
        }
        
        // idx 점수에서 포기하는 경우
        lion[idx] = 0;
        dfs(apeach, lion, idx + 1, cnt);
    }

    public static void main(String[] args) {
        int n = 10;
        int[] info = { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 };
        int[] answer = new int[11];
        int[] fail = { -1 };
        
        memo = fail;
        dfs(info, answer, 0, n);
        
        int sum = Arrays.stream(memo).sum();

        //if (sum > 0 && n != sum) memo[10] = n - sum;

        System.out.println(Arrays.toString(memo));
    }
}
