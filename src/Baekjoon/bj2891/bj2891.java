package Baekjoon.bj2891;
// https://www.acmicpc.net/problem/2891
import java.util.*;

public class bj2891 {
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer = 0;

        int n = sc.nextInt();
        int s = sc.nextInt();
        int r = sc.nextInt();

        int[] canoe = new int[n];

        
        // 손상된 팀
        for (int i = 0; i < s; i++) {
            canoe[sc.nextInt() - 1]--;
        }

        // 여유인 팀
        for (int i = 0; i < r; i++) {
            canoe[sc.nextInt() - 1]++;
        }

        dfs(n, 0, canoe);

        answer = Collections.min(list);

        System.out.println(answer);
    }

    static void dfs(int n, int cur, int[] canoe) {
        // 체킹을 다 한 경우 부족한 팀의 수 list에 추가
        if (cur == n) {
            list.add(countFailTeam(canoe));
            return;
        }

        // 나눠줄수 있는 팀인 경우 주변 확인
        if (canoe[cur] == 1) {
            // 앞 팀에게 나눠줄수 있는 경우
            if (cur - 1 >= 0 && canoe[cur - 1] == -1) {
                // 나눠주기
                canoe[cur - 1] = 0;
                canoe[cur] = 0;
                // dfs
                dfs(n, cur + 1, canoe);
                // 되돌려놓기
                canoe[cur - 1] = -1;
                canoe[cur] = 1;
            }

            // 뒤 팀에게 나눠줄수 있는 경우
            if (cur + 1 < n && canoe[cur + 1] == -1) {
                // 나눠주기
                canoe[cur + 1] = 0;
                canoe[cur] = 0;
                // dfs
                dfs(n, cur + 1, canoe);
                // 되돌려놓기
                canoe[cur + 1] = -1;
                canoe[cur] = 1;
            }
        }

        // 나눠 줄데가 없는 경우
        dfs(n, cur + 1, canoe);
    }

    static int countFailTeam(int[] canoe) {
        int cnt = 0;
        for (int c: canoe) {
            if (c == -1) cnt++;
        }

        return cnt;
    }
}
