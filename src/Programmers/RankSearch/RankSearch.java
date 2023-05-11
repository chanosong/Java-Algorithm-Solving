package Programmers.RankSearch;
// 순위 검색
// https://school.programmers.co.kr/learn/courses/30/lessons/72412
import java.util.*;

public class RankSearch {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
        new String[] {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"})));
    }

    static Map<String, List<Integer>> map;

    static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        map = new HashMap<>();

        // info 파싱
        for (String s : info) {
            String[] data = s.split(" ");

            // dfs로 데이터 삽입
            dfs2("", data, 0);

            // System.out.println(list.size());
        }

        // 값들 모두 정렬
        for (List<Integer> s :map.values()) {
            Collections.sort(s);
        }

        // query 결과물
        for (int i = 0; i < query.length; i++) {
            // query 파싱
            String[] parsedQuery = query[i].split(" ");

            StringBuilder sb = new StringBuilder();

            // 점수를 제외한 String 만들기
            for (int j = 0; j < parsedQuery.length - 1; j++) {

                if (parsedQuery[j].equals("and")) continue;
                sb.append(parsedQuery[j]);
            }

            if (!map.containsKey(sb.toString())) answer[i] = 0;
            else {
                List<Integer> score = map.get(sb.toString());

                int idx = getIdx(score, Integer.parseInt(parsedQuery[parsedQuery.length - 1]), 0, score.size() - 1);
                answer[i] = score.size() - idx;
            }
        }

        return answer;
    }

    // 입력받은 target보다 큰 수들 중 가장 작은 idx 반환
    static int getIdx(List<Integer> score, int target, int start, int end) {

        while (start <= end) {
            int mid = (start + end) / 2;
            int midNum = score.get(mid);

            if (midNum >= target) end = mid - 1;
            else start = mid + 1;
        }

        return start;
    }

    static void dfs(List<String> list, String[] data, boolean[] used, int cursor) {
        if (cursor >= data.length - 1) {
            // 다 체크했을 경우
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < used.length; i++) {
                if (used[i]) sb.append(data[i]);
                else sb.append("-");
            }

            // list에 새로운 케이스 추가 후 종료
            list.add(sb.toString());
            return;
        }

        // 아직인 다 고르지 못한 경우

        dfs(list, data, used,cursor + 1);
        used[cursor] = true;
        dfs(list, data, used, cursor + 1);
        used[cursor] = false;
    }

    static void dfs2(String str, String[] data, int cursor) {
        if (cursor >= data.length - 1) {
            // 다 체크했을 경우
            // List가 없는 경우 미리 생성
            if (!map.containsKey(str)) map.put(str, new ArrayList<>());
            // 데이터 추가
            map.get(str).add(Integer.parseInt(data[data.length - 1]));
            return;
        }

        // 아직 다 고르지 못한 경우
        dfs2(str + "-", data,cursor + 1);
        dfs2(str + data[cursor], data, cursor + 1);
    }
}
