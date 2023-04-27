package Programmers.FailRate;
// https://school.programmers.co.kr/learn/courses/30/lessons/42889
// 실패율

import java.util.Arrays;
import java.util.PriorityQueue;

public class FailRate {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(5, new int[]{2,1,2,6,2,4,3,3})));
        System.out.println(Arrays.toString(solution(4, new int[]{4,4,4,4,4})));
    }

    static int[] solution(int N, int[] stages) {

        int[] cnt = new int[N + 1];

        // 각 스테이지 별 정체된 유저 수
        for (int s : stages) {
            cnt[s - 1]++;
        }

        // 뒤에서부터 카운팅하면서 실패율 계산
        int userCnt = cnt[cnt.length - 1];

        PriorityQueue<StageInfo> pq = new PriorityQueue<>();

        for (int i = cnt.length - 2; i >= 0; i--) {
            // 누적 유저 증가
            userCnt += cnt[i];

            pq.add(new StageInfo(i + 1, userCnt, cnt[i]));
        }

        int[] answer = new int[pq.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = pq.poll().stageNum;
        }

        return answer;
    }
}

class StageInfo implements Comparable {
    int stageNum;
    double rate;

    StageInfo(int stageNum, int trial, int fail) {
        this.stageNum = stageNum;
        if (trial != 0) rate = (double) fail / trial;
        else rate = 0;
    }

    @Override
    public int compareTo(Object o) {
        StageInfo s = (StageInfo) o;
        if (this.rate < s.rate) return 1;
        else if (this.rate == s.rate) {
            if (this.stageNum < s.stageNum) return -1;
            return 1;
        }
        else return -1;
    }

}