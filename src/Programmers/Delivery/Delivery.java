package Programmers.Delivery;
// https://school.programmers.co.kr/learn/courses/30/lessons/12978
import java.util.*;

public class Delivery {
    public static void main(String[] args) {
        /*
        int N = 5;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int K = 3;
          */
        int N = 6; int K = 4;
        int[][] road = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
        int answer = 0;

        int[][] map = new int[N][N];
        timeList = new int[N];
        Arrays.fill(timeList, -1);

        // map 다시 그리기
        for (int i = 0; i < road.length; i++) {
            if (map[road[i][0] - 1][road[i][1] - 1] == 0 || map[road[i][0] - 1][road[i][1] - 1] > road[i][2]){
                map[road[i][0] - 1][road[i][1] - 1] = road[i][2];
                map[road[i][1] - 1][road[i][0] - 1] = road[i][2];
            }
        }

        dfs(0, K, 1, map);

        for (int t : timeList) {
            if (t >= 0) answer++;
        }

        System.out.println(answer);
    }

    static int[] timeList;
    static void dfs(int time, int K, int start, int[][] map) {

        // 방문체크
        if (timeList[start - 1] != -1 && timeList[start - 1] < time) return;

        timeList[start - 1] = time;

        // 가능한 곳 일단 다 찌르기
        for (int i = 0; i < map.length; i++) {
            // 이어지지 않은 곳인 경우 스킵
            if (map[start - 1][i] == 0) continue;
            int ntime = time + map[start - 1][i];
            // K를 오버하는 곳이면 스킵
            if (ntime > K) continue;
            // 가볼만한 곳이라면 계속 진행
            dfs(ntime, K, i + 1, map);
        }
    }
}
