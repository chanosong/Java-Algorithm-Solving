package Baekjoon.bj15686;

import java.util.*;

// https://www.acmicpc.net/problem/15686
public class bj15686 {

    static int n;
    static int m;
    static int[][] map;
    // 치킨집 좌표 리스트
    static List<Integer[]> chickenPoint;
    // 가정집 좌표 리스트
    static List<Integer[]> housePoint;
    // 조합 경우의 수 리스트
    static List<List<Integer>> cases;
    
    // 조합에 사용될 이미 사용한 것 리스트
    static boolean[] checked;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        // 버퍼 초기화
        sc.nextLine();

        map = new int[n][n];
        // 지도 입력
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 치킨집, 가정집 정보 수집
        chickenPoint = new ArrayList<>();
        housePoint = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) housePoint.add(new Integer[]{i,j});
                else if (map[i][j] == 2) chickenPoint.add(new Integer[]{i,j});
            }
        }

        checked = new boolean[chickenPoint.size()];

        // 도시의 치킨 거리 점수 계산
        int sum = 0;

        // 만약 모든 치킨 집이 살아있어도 되는 경우 그냥 계산
        if (chickenPoint.size() == m) {
            for (Integer[] p : housePoint) {
                sum += calculateDist(p[0], p[1]);
            }
        }
        else {
            // 조합의 결과 List
            cases = new ArrayList<>();

            // 조합 구하기
            comb(0, 0);
            sum = Integer.MAX_VALUE;

            // System.out.println(cases.toString());
            // 케이스별 치킨거리 계산
            for (int i = 0; i < cases.size(); i++){
                int tmp = 0;
                /*
                // 치킨집 철거 적용
                for (int j = 0; j < cases.get(i).size(); j++) {
                    int chickX = chickenPoint.get(cases.get(i).get(j))[0];
                    int chickY = chickenPoint.get(cases.get(i).get(j))[1];

                    map[chickX][chickY] = 0;
                }

                 */
                // 해당 경우의 도시의 치킨 거리 계산
                for (Integer[] p : housePoint){
                    tmp += calculateDist2(p[0], p[1], cases.get(i));
                }

                // 최소값 갱신
                sum = Math.min(sum, tmp);

                /*
                // map 정상화
                for (int j = 0; j < cases.get(i).size(); j++) {
                    int chickX = chickenPoint.get(cases.get(i).get(j))[0];
                    int chickY = chickenPoint.get(cases.get(i).get(j))[1];

                    map[chickX][chickY] = 2;
                }

                 */
            }

        }


        System.out.println(sum);
    }

    // 현재 상태에서 BFS
    static int calculateDist(int x, int y) {
        int distance = 0;

        // BFS
        Queue<Procedure> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        queue.add(new Procedure(x,y,0));
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            Procedure now = queue.poll();

            // 만약 현재가 치킨집인 경우
            if (now.isChick(map)) {
                return now.depth;
            }

            // 아닌 경우 계속 탐사
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nd = now.depth + 1;

                // 벗어난 경우, 이미 간적이 있는 경우 스킵
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;

                // 아닌 경우 탐색 진행
                queue.add(new Procedure(nx,ny,nd));
                visited[nx][ny] = true;
            }
        }

        return distance;
    }

    // 현재 상태에서 BFS말고 그냥 x,y로 부터 가장 가까운 치킨집과의 거리
    // c : 철거되지 않고 남아있는 치킨집들의 index
    static int calculateDist2(int x, int y, List<Integer> c) {
        int distance = Integer.MAX_VALUE;

        // 체크해야하는 치킨집
        boolean[] doCheck = new boolean[chickenPoint.size()];

        // 체크해야하는 곳은 true로
        for (int i : c) doCheck[i] = true;

        for (int i = 0; i < chickenPoint.size(); i++) {
            // 없애는 곳이면 스킵
            if (!doCheck[i]) continue;

            distance = Math.min(distance, Math.abs(x - chickenPoint.get(i)[0]) + Math.abs(y - chickenPoint.get(i)[1]));
        }
        //System.out.println(distance);
        return distance;
    }

    static void comb(int start, int step) {
        // m개 다 고른 경우 종료
        if (step == m) {
            List<Integer> tmpCase = new ArrayList<>();
            for (int i = 0; i < checked.length; i++) {
                // 살릴 치킨집 체크
                if (checked[i]) tmpCase.add(i);
            }
            cases.add(tmpCase);
            //System.out.println(Arrays.toString(checked));
            return;
        }

        for (int i = start; i < chickenPoint.size(); i++) {
            // 이미 선택한 것이면
            if (checked[i]) continue;

            checked[i] = true;
            comb(i + 1, step + 1);
            checked[i] = false;
        }
    }
}

class Procedure {
    int x;
    int y;
    int depth;

    Procedure(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    boolean isChick(int[][] map) {
        if (map[x][y] == 2) return true;
        return false;
    }
}