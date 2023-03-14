package Programmers.CoverUp;
// https://school.programmers.co.kr/learn/courses/30/lessons/161989
public class CoverUp {
    public static void main(String[] args) {
        int n = 8;
        int m = 4;
        int[] section = {2,3,6};
        int answer = 0;
        // 칠해진 값
        int cur = 0;
        // section의 인덱스
        int idx = 0;

        while (true) {

            // 현재 커서가 마지막인 경우
            if (cur + 1 >= section.length) break;

            // 횟수 증가
            answer++;
            // 다음 목표 체크
            idx++;
            // 오버한 경우 종료
            if (section[idx] - section[cur] + 1 <= m) break;

        }
        /*
        for (int i = 1; i < section.length; i++) {
            // 다음 섹션 까지 길이가 더 길 경우
            // 붓질 수 추가하고 커서 옮기기
            if (section[i] - cur + 1 > m) {
                answer++;
                cur = section[i];
                continue;
            }

            // 다음 섹션 까지 길이가 되는 경우
            // 우선 다음 섹션으로 다음 좌표 지정
            int next = section[i];
            int idx = i;
            while (true) {

                // 다다음 섹션 확인 후 거기까지는 안되는 경우 캔슬
                if (section[idx + 1] - cur + 1 > m) break;
                // 다다음 섹션 또한 사정거리 내인 경우
                next = section[++idx];
            }
        }
         */
        System.out.println(answer);
    }
}
