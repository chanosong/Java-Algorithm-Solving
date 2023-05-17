package Programmers.SheepAndWolf;
// https://school.programmers.co.kr/learn/courses/30/lessons/92343
// 양과 늑대

public class SheepAndWolf {

    static int[] sInfo;
    static int[][] sEdges;
    static int maxSheepCnt;
    public static void main(String[] args) {
        System.out.println(solution(new int[]{0,0,1,1,1,0,1,0,1,0,1,1},
                new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}}));
        System.out.println(solution(new int[]{0,1,0,1,1,0,1,0,0,1,0},
                new int[][]{{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}}));
    }

    // DFS사용 버전
    static int solution(int[] info, int[][] edges) {
        maxSheepCnt = 0;

        sInfo = info;
        sEdges = edges;
        boolean[] visited = new boolean[info.length];
        dfs(0, visited, 0, 0);


        return maxSheepCnt;
    }

    static void dfs(int startIdx, boolean[] visited, int sheepCnt, int wolfCnt) {
        visited[startIdx] = true;
        if (sInfo[startIdx] == 0) {
            sheepCnt++;
            if (sheepCnt > maxSheepCnt) {
                maxSheepCnt = sheepCnt;
            }
        }
        else wolfCnt++;

        // 잡아먹히는 경우 종료
        if (sheepCnt <= wolfCnt) return;

        for (int[] edge : sEdges) {
            if (visited[edge[0]] && !visited[edge[1]]) {
                boolean[] newVisited = new boolean[visited.length];
                for (int i = 0; i < visited.length; i++) {
                    newVisited[i] = visited[i];
                }

                dfs(edge[1], newVisited, sheepCnt, wolfCnt);
            }
        }

    }

    // BFS 사용 버전
    /*
    static int solution(int[] info, int[][] edges) {
        int answer = 0;

        // 각 노드를 찾아갈 list
        Node[] nodeList = new Node[info.length];
        boolean[] visited = new boolean[info.length];

        // root Node 설정
        nodeList[0] = new Node(0, info[0]);

        // nodeList 세팅
        for (int i = 0; i < info.length; i++) {
            // 생성
            Node newNode = new Node(i, info[i]);
            // nodeList에 데이터 삽입
            nodeList[i] = newNode;
        }
        
        // edge 연결
        for (int i = 0; i < edges.length; i++) {
            nodeList[edges[i][0]].addNode(nodeList[edges[i][1]]);
        }

        Queue<List<Node>> queue = new LinkedList<>();
        List<Node> history = new ArrayList<>();
        history.add(nodeList[0]);
        visited[0] = true;

        queue.add(history);

        // BFS
        while (!queue.isEmpty()) {
            List<Node> now = queue.poll();
            Node current = now.get(now.size() - 1);

            if (current.name.equals("sheep")) answer++;

            // 현재 양, 늑대 상황 확인
            int sheepCnt = 0;
            int wolfCnt = 0;

            for (Node n : now) {
                if (n.name.equals("sheep")) {
                    sheepCnt++;
                }
                else wolfCnt++;
            }

            // 더이상 갈 곳이 없는 경우 종료
            if (current.left == null) continue;


            // 왼쪽을 갈 수 있는 경우
            if (current.left.name.equals("sheep") || sheepCnt - 1 > wolfCnt) {
                // 왼쪽가는 경우
                List<Node> leftSelectHistory = new ArrayList<>();

                leftSelectHistory.addAll(now);
                leftSelectHistory.add(current.left);
                queue.add(leftSelectHistory);
                visited[current.left.idx] = true;
            }

            // 오른쪽을 갈 수 없는 경우 스킵
            if (current.right == null) continue;

            // 오른쪽을 갈 수 있는 경우
            if (current.right.name.equals("sheep") || sheepCnt - 1 > wolfCnt) {
                // 왼쪽가는 경우
                List<Node> rightSelectHistory = new ArrayList<>();

                rightSelectHistory.addAll(now);
                rightSelectHistory.add(current.right);
                queue.add(rightSelectHistory);
                visited[current.right.idx] = true;
            }
        }

        return answer;
    }
    */
}

class Node {
    int idx;
    String name;
    Node left;
    Node right;

    Node(int idx, int n) {
        this.idx = idx;
        if (n == 0) name = "sheep";
        else name = "wolf";

        left = null;
        right = null;
    }

    public void addNode(Node node) {
        if (left == null) {
            left = node;
        }
        else {
            right = node;
        }
    }
}
