package Programmers.ArcheryContest;
/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/92342
 */
import java.util.*;

// 총 n발의 화살을 쏴서 이걸 info와 같은 idx에 들어간 경우 패배, 무조건 피해서 고득점으로 노려야한다.

public class ArcheryContest {

    static int max = 0;

    // lion 기준 스코어 계산
    static int calculate(int[] apeach, int[] lion) {
        int scoreGap = 0;

        for (int i = 0; i < 11; i++) {
            if (apeach[i] >= lion[i]) scoreGap -= 10 - i;
            else scoreGap += 10 - i;
        }        

        return scoreGap;
    }

    static void dfs(int[] apeach, int[] lion, int depth) {
        if (calculate(apeach, lion) > 0) {
            if (max < )
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[] info = {2,1,1,1,0,0,0,0,0,0,0};
        int[] answer = new int[11];
        int[] fail = {-1};

        for (int i = 10; i >=0; i--) {
            if (info[i] == 0) {
                answer[i] = 1;
            }
        }

        System.out.println(Arrays.toString(answer));
    }
}
