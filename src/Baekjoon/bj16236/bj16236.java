package Baekjoon.bj16236;
// https://www.acmicpc.net/problem/16236

import java.util.*;

public class bj16236 {
    static int[][] map;

    // 총 소요 시간
    static int time;

    // 아기상어 좌표
    static Shark baby;

    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};

    // 먹이탐색 시 활용할 지도
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        sc.nextLine();
        // 지도 정보 업데이트
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 아기상어 생성
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 9) {
                    baby = new Shark(i,j);
                }
            }
        }

        // 시간 초기화
        time = 0;

        // 될 때 까지 이동
        while (move()) {
            //System.out.println(time);
        }

        System.out.println(time);
    }

    // 이동
    static boolean move() {

        // 목표 먹이의 좌표를 갖는 배열
        // 먹을 수 있는 먹이아 없는 경우 먹이 좌표는 -1, -1을 갖는다
        Integer[] positions = scan();
        
        // 먹을 수 있는 먹이가 없는 경우 종료
        if (positions[0] == Integer.MAX_VALUE) return false;


        // 먹을 수 있는 먹이가 있는 경우
        // 상어 이동
        map[baby.x][baby.y] = 0;
        map[positions[0]][positions[1]] = 9;
        baby.move(positions[0], positions[1]);
        // 시간 증가
        time += positions[2];
        return true;
    }

    
    static Integer[] scan() {
        Integer[] goal = new Integer[]{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};

        Queue<Integer[]> queue = new LinkedList<>();
        // 지도 초기화
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(visited[i], false);
        }

        queue.add(new Integer[]{baby.x, baby.y, 0});
        visited[baby.x][baby.y] = true;

        // 갈 수 있는 후보군
        List<Integer[]> list = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            Integer[] now = queue.poll();

            // 먹을 수 있는 경우
            if (baby.canEat(map[now[0]][now[1]])) {
                list.add(now);
                continue;
            }

            // 먹을 수 없는 경우 사방 확인
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                int nd = now[2] + 1;

                // 맵을 벗어나거나 이미 간 곳이거나 통과도 못하는 경우 스킵
                if (nx < 0 || nx >= map.length || ny < 0 || ny >= map.length || visited[nx][ny] || !baby.canPass(map[nx][ny])) continue;

                // 통과 가능한 경우
                queue.add(new Integer[]{nx,ny,nd});
                visited[nx][ny] = true;
            }
        }


        for (Integer[] l : list) {
            // 더 짧은 경우
            if (goal[2] > l[2]) {
                goal = l.clone();
            }
            // 같은 거리인 경우
            else if (goal[2] == l[2]) {
                // 더 위에 있는 경우
                if (goal[0] > l[0]) {
                    goal = l.clone();
                }
                // 같은 높이인 경우
                else if (goal[0] == l[0]) {
                    // 더 왼쪽에 있는 경우
                    if (goal[1] > l[1]) {
                        goal = l.clone();
                    }
                }
            }
        }


        return goal;
    }
}

class Shark {
    // 좌표
    int x;
    int y;
    // 무게
    int weight;
    // 먹은 수
    int feed;

    Shark(int x, int y) {
        this.x = x;
        this.y = y;
        this.weight = 2;
        this.feed = 0;
    }

    // 먹을 수 있는지 확인
    boolean canEat(int w) {
        if (weight > w && w != 0) return true;
        return false;
    }

    // 지나갈 수 있는지 확인
    boolean canPass(int w) {
        if (weight >= w) return true;
        return false;
    }

    void move(int x, int y) {
        this.x = x;
        this.y = y;
        feed += 1;
        
        // 진화 가능하면 진화
        if (weight == feed) {
            if (weight <= 7){
                weight += 1;
            }
            feed = 0;
        }
    }
}
