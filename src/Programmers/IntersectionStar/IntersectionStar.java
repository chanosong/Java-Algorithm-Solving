package Programmers.IntersectionStar;
// https://school.programmers.co.kr/learn/courses/30/lessons/87377
import java.util.*;

public class IntersectionStar {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[][]{{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}})));
        System.out.println(Arrays.toString(solution(new int[][]{{0, 1, -1}, {1, 0, -1}, {1, 0, 1}})));
        System.out.println(Arrays.toString(solution(new int[][]{{1, -1, 0}, {2, -1, 0}})));
        System.out.println(Arrays.toString(solution(new int[][]{{1, -1, 0}, {2, -1, 0}, {4, -1, 0}})));
    }

    static String[] solution (int[][] line) {
        Set<Point> intersection = new HashSet<>();

        // 조합을 사용하여 교점 좌표 구하기
        comb(0, line.length, 2, new boolean[line.length], line, new long[2][3], intersection);

        /*
        for (Point i : intersection) {
            System.out.println(i.x + " " + i.y);
        }

         */

        // 교점 좌표의 최대 최소값 구하기
        long[] minP = new long[] {Long.MAX_VALUE, Long.MAX_VALUE};
        long[] maxP = new long[] {Long.MIN_VALUE, Long.MIN_VALUE};

        // 최대 최소 좌표 갱신
        for (Point p : intersection) {
            if (p.x > maxP[0]) maxP[0] = p.x;
            if (p.x < minP[0]) minP[0] = p.x;
            if (p.y > maxP[1]) maxP[1] = p.y;
            if (p.y < minP[1]) minP[1] = p.y;
        }

        // 차이 구하기
        long row = maxP[1] - minP[1] + 1;
        long col = maxP[0] - minP[0] + 1;

        StringBuffer[] map = new StringBuffer[(int) row];

        // 뼈대 만들어주기
        for (int i = 0; i < row; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < col; j++) {
                sb.append(".");
            }
            map[i] = sb;
        }

        // 별 찍기
        for (Point p : intersection) {
            // 별 찍을 좌표
            long sx = p.x - minP[0];
            long sy = maxP[1] - p.y;

            map[Math.toIntExact(sy)].replace((int) sx, (int) (sx+1), "*");
        }

        /*
        for(int i = 0; i < map.length; i++) {
            System.out.println(map[i]);
        }

         */

        String[] strMap = new String[Math.toIntExact(row)];

        for (int i = 0; i < row; i++) {
            strMap[i] = map[i].toString();
        }

        return strMap;
    }

    static void comb(int start, int n, int r, boolean[] visited,int[][] line, long[][] selected, Set<Point> intersection) {
        // 모두 고른 경우
        if (r == 0) {
            // selected[0] -> A B E, selected[1] -> C D F

            long A = selected[0][0];
            long B = selected[0][1];
            long E = selected[0][2];

            long C = selected[1][0];
            long D = selected[1][1];
            long F = selected[1][2];

            // 교점이 없는 경우 스킵
            if (A*D - B*C == 0) return;

            // 교점이 있는 경우 계산
            double lx = (double) (B*F - E*D) / (double) (A*D - B*C);
            double ly = (double) (E*C - A*F) / (double) (A*D - B*C);
        
            // 만약 교점 좌표가 실수인 경우 스킵
            if (Math.floor(lx) != lx || Math.floor(ly) != ly) return;

            // 교점 좌표사 정수인 경우
            intersection.add(new Point((long) lx, (long) ly));
            return;
        }
        
        // 아직 남은 경우

        for (int i = start; i < n; i++) {
            visited[i] = true;
            // 선택
            selected[2 - r][0] = line[i][0];
            selected[2 - r][1] = line[i][1];
            selected[2 - r][2] = line[i][2];
            comb(start + 1, n, r - 1,visited, line, selected, intersection);
            visited[i] = false;
        }
    }
}

class Point {
    long x;
    long y;

    Point (long x ,long y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) return false;
        return (((Point) obj).x == this.x) && (((Point) obj).y == this.y);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
