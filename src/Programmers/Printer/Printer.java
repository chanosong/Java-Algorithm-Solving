package Programmers.Printer;
//https://school.programmers.co.kr/learn/courses/30/lessons/42587
import java.util.*;

public class Printer {

    static class Node {
        int v;
        boolean want;

        Node(int v, boolean want) {
            this.v = v;
            this.want = want;
        }

        Node(int v) {
            this.v = v;
            this.want = false;
        }
    }

    public static void main(String[] args) {
        int[] priorities = {1,1,9,1,1,1};
        int location = 0;
        int answer = 1;
        int len = priorities.length;

        Queue<Node> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        // 초반 세팅
        for (int i = 0; i < len; i++) {
            if (i == location) {
                queue.add(new Node(priorities[i], true));
            }
            else {
                queue.add(new Node(priorities[i]));
            }
            list.add(priorities[i]);
        }

        list.sort(Comparator.reverseOrder());

        // 작업 진행
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            // 최대가 아닌 경우
            if (n.v != list.get(0)){
                queue.add(n);
                continue;
            }
            // 최대인 경우 삭제
            list.remove(0);
            // 최대이면서 찾던 것이면 종료
            if (n.want) break;
            answer++;
        }

        System.out.println(answer);
    }
}
