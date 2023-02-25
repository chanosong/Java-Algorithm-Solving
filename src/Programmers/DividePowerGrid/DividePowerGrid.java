package Programmers.DividePowerGrid;
import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/86971
public class DividePowerGrid {
    public static void main(String[] args) {
        int n = 9;
        int[][] wires = //{{1,2},{2,3},{3,4}};
                {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        int min = n;

        for (int i = 0; i < wires.length; i++) {
            List<Integer[]> list = new ArrayList<>();

            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;

                list.add(Arrays.stream(wires[j]).boxed().toArray(Integer[]::new));
            }

            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(list.get(0)[0], list.get(0)[1]));
            boolean[] used = new boolean[list.size()];
            used[0] = true;

            while (!queue.isEmpty()) {
                Node now = queue.poll();

                for (int j = 0; j < list.size(); j++) {
                    // 이미 사용한 경우 스킵
                    if (used[j]) continue;

                    int nx = list.get(j)[0];
                    int ny = list.get(j)[1];

                    // 연결되어있는 노드라면 add 후 used 체크
                    if (now.isLinked(nx,ny)) {
                        queue.add(new Node(nx,ny));
                        used[j] = true;
                    }
                }
            }
            int cnt = 0;
            for (boolean b : used) {
                if (b) cnt++;
            }

            min = Math.min(min, Math.abs(n - (cnt + 1) * 2));
        }

        System.out.println(min);
    }
}

class Node {
    int x;
    int y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean isLinked(int x, int y) {
        return this.x == x || this.x == y || this.y == x || this.y == y;    }
}
