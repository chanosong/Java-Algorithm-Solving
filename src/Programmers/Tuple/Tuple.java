package Programmers.Tuple;
// https://school.programmers.co.kr/learn/courses/30/lessons/64065
// 튜플
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Tuple {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
        System.out.println(Arrays.toString(solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")));
        System.out.println(Arrays.toString(solution("{{20,111},{111}}")));
        System.out.println(Arrays.toString(solution("{{123}}")));
        System.out.println(Arrays.toString(solution("{{4,2,3},{3},{2,3,4,1},{2,3}}")));
    }

    static int[] solution(String s) {
        int[] answer;

        s = s.substring(2, s.length() - 2);

        String[] strPart = s.split("},\\{");

        // 우큐
        PriorityQueue<CustomTuple> pq = new PriorityQueue<>();

        // 삽입
        for (String strChunk : strPart) {
            pq.add(new CustomTuple(strChunk));
        }

        // 길이 설정
        answer = new int[pq.size()];
        
        List<String> used = new ArrayList<>();

        // 반복
        while (!pq.isEmpty()) {
            CustomTuple now = pq.poll();

            for (int i = 0; i < now.list.size(); i++) {
                if (used.contains(now.list.get(i))) continue;

                used.add(now.list.get(i));
            }
        }

        for (int i = 0; i < used.size(); i++) {
            answer[i] = Integer.parseInt(used.get(i));
        }

        return answer;
    }
}

class CustomTuple implements Comparable<CustomTuple> {
    List<String> list;

    CustomTuple(String str) {
        list = new ArrayList<>();

        String[] s = str.split(",");

        for (String strChunk : s) {
            list.add(strChunk);
        }
    }

    @Override
    public int compareTo(CustomTuple ct) {
        if (this.list.size() > ct.list.size()) return 1;
        else return -1;
    }
}